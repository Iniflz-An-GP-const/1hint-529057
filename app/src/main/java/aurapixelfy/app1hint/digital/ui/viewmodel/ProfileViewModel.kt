package aurapixelfy.app1hint.digital.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aurapixelfy.app1hint.digital.data.datastore.DEFAULT_WEEKLY_GOAL
import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.data.model.estimatedMinutes
import aurapixelfy.app1hint.digital.data.repository.ProfileRepository
import aurapixelfy.app1hint.digital.data.repository.WorkoutRepository
import aurapixelfy.app1hint.digital.data.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MAX_DIGITS = 3
private const val MAX_NAME_LENGTH = 40

data class ProfileForm(
    val name: String = "",
    val ageText: String = "",
    val fitnessLevel: Difficulty = Difficulty.BEGINNER,
    val weeklyGoalText: String = "",
    val heightText: String = "",
    val weightText: String = "",
)

data class ProfileStats(
    val workouts: Int = 0,
    val hours: Float = 0f,
    val daysActive: Int = 0,
)

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    private val savedForm = MutableStateFlow<ProfileForm?>(null)

    private val _form = MutableStateFlow(ProfileForm())
    val form: StateFlow<ProfileForm> = _form.asStateFlow()

    /** True while the edited values differ from the stored ones — the Save button shows then. */
    val hasChanges: StateFlow<Boolean> =
        combine(savedForm, _form) { saved, edited -> saved != null && saved != edited }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false,
            )

    val stats: StateFlow<ProfileStats> =
        workoutSessionRepository.observeAll()
            .map { sessions ->
                val minutes = sessions.sumOf { session ->
                    workoutRepository.getById(session.workoutId)?.estimatedMinutes() ?: 0
                }
                ProfileStats(
                    workouts = sessions.size,
                    hours = minutes / 60f,
                    daysActive = sessions.map { it.timestamp.toLocalDate() }.distinct().size,
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ProfileStats(),
            )

    init {
        viewModelScope.launch {
            combine(
                profileRepository.observeName(),
                profileRepository.observeAge(),
                profileRepository.observeFitnessLevel(),
                profileRepository.observeWeeklyGoal(),
                combine(
                    profileRepository.observeHeightCm(),
                    profileRepository.observeWeightKg(),
                ) { height, weight -> height to weight },
            ) { name, age, level, weeklyGoal, (height, weight) ->
                ProfileForm(
                    name = name,
                    ageText = age?.toString().orEmpty(),
                    fitnessLevel = Difficulty.entries.firstOrNull { it.name == level } ?: Difficulty.BEGINNER,
                    weeklyGoalText = weeklyGoal.toString(),
                    heightText = height?.toString().orEmpty(),
                    weightText = weight?.toString().orEmpty(),
                )
            }.collect { stored ->
                val previous = savedForm.value
                // Unsaved edits stay in place; otherwise the form follows the stored values.
                if (previous == null || previous == _form.value) {
                    _form.value = stored
                }
                savedForm.value = stored
            }
        }
    }

    fun onNameChanged(text: String) {
        if (text.length > MAX_NAME_LENGTH) return
        _form.update { it.copy(name = text) }
    }

    fun onAgeChanged(text: String) {
        if (!isValidDigitsInput(text)) return
        _form.update { it.copy(ageText = text) }
    }

    fun onFitnessLevelChanged(level: Difficulty) {
        _form.update { it.copy(fitnessLevel = level) }
    }

    fun onWeeklyGoalChanged(text: String) {
        if (!isValidDigitsInput(text)) return
        _form.update { it.copy(weeklyGoalText = text) }
    }

    fun onHeightChanged(text: String) {
        if (!isValidDigitsInput(text)) return
        _form.update { it.copy(heightText = text) }
    }

    fun onWeightChanged(text: String) {
        if (!isValidDigitsInput(text)) return
        _form.update { it.copy(weightText = text) }
    }

    fun onSaveClick() {
        // Normalized up front so the form equals what the store returns and Save hides again.
        val edited = _form.value.let { form ->
            form.copy(
                name = form.name.trim(),
                ageText = form.ageText.toIntOrNull()?.toString().orEmpty(),
                weeklyGoalText = (form.weeklyGoalText.toIntOrNull() ?: DEFAULT_WEEKLY_GOAL).toString(),
                heightText = form.heightText.toIntOrNull()?.toString().orEmpty(),
                weightText = form.weightText.toIntOrNull()?.toString().orEmpty(),
            )
        }
        _form.value = edited
        viewModelScope.launch {
            profileRepository.setProfile(
                name = edited.name,
                age = edited.ageText.toIntOrNull(),
                fitnessLevel = edited.fitnessLevel.name,
            )
            profileRepository.setWeeklyGoal(edited.weeklyGoalText.toIntOrNull())
            profileRepository.setHeightCm(edited.heightText.toIntOrNull())
            profileRepository.setWeightKg(edited.weightText.toIntOrNull())
        }
    }

    fun onResetDataConfirmed() {
        viewModelScope.launch {
            workoutSessionRepository.deleteAll()
        }
    }

    private fun isValidDigitsInput(text: String): Boolean {
        return text.length <= MAX_DIGITS && text.all { it.isDigit() }
    }
}
