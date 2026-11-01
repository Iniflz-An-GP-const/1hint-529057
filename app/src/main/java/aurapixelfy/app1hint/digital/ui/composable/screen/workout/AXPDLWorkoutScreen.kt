package aurapixelfy.app1hint.digital.ui.composable.screen.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.model.ExerciseModel
import aurapixelfy.app1hint.digital.data.model.WorkoutModel
import aurapixelfy.app1hint.digital.data.model.estimatedMinutes
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.DataBasedContainer
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.DataEmptyContent
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.WorkoutImage
import aurapixelfy.app1hint.digital.ui.composable.screen.workout.actionblock.WorkoutActionBlock
import aurapixelfy.app1hint.digital.ui.composable.screen.workout.typedifficulty.WorkoutTypeDifficulty
import aurapixelfy.app1hint.digital.ui.state.DataUiState
import aurapixelfy.app1hint.digital.ui.state.WorkoutUiState
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.WorkoutViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private val HeroHeight = 260.dp
private val HeroCornerRadius = 24.dp
private val NumberBadgeSize = 32.dp
private val InfoIconSize = 18.dp

@Composable
fun AXPDLWorkoutScreen(
    workoutId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkoutViewModel = koinViewModel { parametersOf(workoutId) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DataBasedContainer(
        modifier = modifier.fillMaxSize(),
        dataState = uiState,

        dataPopulated = {
            val state = (uiState as DataUiState.Populated<WorkoutUiState>).data

            WorkoutDetailsContent(
                workout = state.workout,
                onCompleteConfirmed = { viewModel.onCompleteWorkoutConfirmed(onSaved = onNavigateBack) },
            )
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.workout_details_state_empty),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun WorkoutDetailsContent(
    workout: WorkoutModel,
    onCompleteConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    var showCompleteDialog by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = dimens.screenPadding),
        ) {
            item {
                WorkoutImage(
                    imageUrl = workout.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HeroHeight)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = HeroCornerRadius,
                                bottomEnd = HeroCornerRadius,
                            )
                        ),
                )
            }

            item {
                WorkoutHeader(
                    workout = workout,
                    modifier = Modifier.padding(dimens.screenPadding),
                )
            }

            item {
                Text(
                    text = stringResource(R.string.workout_details_exercises_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(
                        start = dimens.screenPadding,
                        end = dimens.screenPadding,
                        bottom = dimens.itemSpacing,
                    ),
                )
            }

            itemsIndexed(workout.exercises) { index, exercise ->
                ExerciseRow(
                    number = index + 1,
                    exercise = exercise,
                    modifier = Modifier.background(
                        if (index % 2 == 0) {
                            MaterialTheme.colorScheme.surface
                        } else {
                            MaterialTheme.colorScheme.background
                        }
                    ),
                )
            }
        }

        WorkoutActionBlock(
            isInProgress = false,
            onClick = { showCompleteDialog = true },
        )
    }

    if (showCompleteDialog) {
        AlertDialog(
            onDismissRequest = { showCompleteDialog = false },
            title = { Text(stringResource(R.string.complete_workout_dialog_title)) },
            text = { Text(stringResource(R.string.complete_workout_dialog_message)) },
            confirmButton = {
                TextButton(
                    enabled = !isSaving,
                    onClick = {
                        isSaving = true
                        showCompleteDialog = false
                        onCompleteConfirmed()
                    },
                ) {
                    Text(stringResource(R.string.complete_workout_dialog_confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showCompleteDialog = false }) {
                    Text(stringResource(R.string.exercise_dialog_cancel))
                }
            },
        )
    }
}

@Composable
private fun WorkoutHeader(
    workout: WorkoutModel,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = workout.name,
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        WorkoutTypeDifficulty(
            type = workout.type,
            difficulty = workout.difficulty,
        )

        Spacer(modifier = Modifier.height(dimens.contentSpacing))

        Row(horizontalArrangement = Arrangement.spacedBy(dimens.sectionGap)) {
            InfoItem(
                icon = Icons.Filled.Timer,
                text = stringResource(R.string.workout_duration_minutes, workout.estimatedMinutes()),
            )
            InfoItem(
                icon = Icons.AutoMirrored.Filled.FormatListBulleted,
                text = stringResource(R.string.workout_card_exercise_count, workout.exercises.size),
            )
        }
    }
}

@Composable
private fun InfoItem(
    icon: ImageVector,
    text: String,
) {
    val dimens = LocalDimens.current

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(InfoIconSize),
        )

        Spacer(modifier = Modifier.width(dimens.tightSpacing))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ExerciseRow(
    number: Int,
    exercise: ExerciseModel,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimens.screenPadding, vertical = dimens.contentSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(NumberBadgeSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        Spacer(modifier = Modifier.width(dimens.contentSpacing))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            Text(
                text = exercise.workLabel(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        val restMs = exercise.restDuration
        if (restMs != null && restMs > 0L) {
            Spacer(modifier = Modifier.width(dimens.itemSpacing))

            Text(
                text = stringResource(R.string.exercise_rest_format, (restMs / 1000).toInt()),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun ExerciseModel.workLabel(): String {
    return if (sets != null && repetitions != null) {
        stringResource(R.string.exercise_sets_reps, sets, repetitions)
    } else {
        val seconds = ((duration ?: 0L) / 1000).toInt()
        stringResource(R.string.exercise_duration_seconds, seconds)
    }
}
