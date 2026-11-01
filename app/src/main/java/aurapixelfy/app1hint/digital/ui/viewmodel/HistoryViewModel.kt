package aurapixelfy.app1hint.digital.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity
import aurapixelfy.app1hint.digital.data.model.estimatedMinutes
import aurapixelfy.app1hint.digital.data.repository.WorkoutRepository
import aurapixelfy.app1hint.digital.data.repository.WorkoutSessionRepository
import aurapixelfy.app1hint.digital.ui.state.DataUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    workoutSessionRepository: WorkoutSessionRepository,
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    /** Sessions newest first, as the DAO returns them. */
    val sessionsState: StateFlow<DataUiState<List<WorkoutSessionEntity>>> =
        workoutSessionRepository.observeAll()
            .map { sessions -> DataUiState.from(sessions) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DataUiState.Initial,
            )

    fun durationMinutes(workoutId: Int): Int {
        return workoutRepository.getById(workoutId)?.estimatedMinutes() ?: 0
    }
}
