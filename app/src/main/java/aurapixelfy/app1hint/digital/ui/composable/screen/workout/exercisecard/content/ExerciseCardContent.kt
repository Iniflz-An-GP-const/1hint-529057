package aurapixelfy.app1hint.digital.ui.composable.screen.workout.exercisecard.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.model.ExerciseModel
import aurapixelfy.app1hint.digital.ui.state.ExerciseItemUiState
import aurapixelfy.app1hint.digital.ui.state.ExercisePhase
import aurapixelfy.app1hint.digital.ui.state.ExerciseStatus
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

@Composable
fun ExerciseCardContent(item: ExerciseItemUiState) {
    val dimens = LocalDimens.current
    val isActive = item.status == ExerciseStatus.ACTIVE
    val isCompleted = item.status == ExerciseStatus.COMPLETED

    Text(
        text = item.exercise.name,
        style = MaterialTheme.typography.titleMedium,
        color = if (isCompleted) {
            MaterialTheme.colorScheme.onSurfaceVariant
        } else {
            MaterialTheme.colorScheme.onSurface
        },
        textDecoration = if (isCompleted) TextDecoration.LineThrough else null,
        minLines = 2,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )

    Spacer(modifier = Modifier.height(dimens.tightSpacing))

    Text(
        text = if (isActive) item.activeSubtitle() else item.exercise.subtitle(),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun ExerciseItemUiState.activeSubtitle(): String {
    val phaseLabel = when (phase) {
        ExercisePhase.WORK -> stringResource(R.string.exercise_phase_work)
        ExercisePhase.REST -> stringResource(R.string.exercise_phase_rest)
    }
    return stringResource(
        R.string.exercise_active_subtitle,
        stringResource(R.string.exercise_set_progress, currentSet, totalSets),
        phaseLabel,
    )
}

@Composable
private fun ExerciseModel.subtitle(): String {
    return if (sets != null && repetitions != null) {
        stringResource(R.string.exercise_sets_reps, sets, repetitions)
    } else {
        val seconds = ((duration ?: 0L) / 1000).toInt()
        stringResource(R.string.exercise_duration_seconds, seconds)
    }
}
