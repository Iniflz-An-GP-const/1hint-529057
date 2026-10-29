package aurapixelfy.app1hint.digital.ui.composable.screen.home.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.data.model.WorkoutType
import aurapixelfy.app1hint.digital.ui.composable.screen.home.filter.content.FilterChipContent
import aurapixelfy.app1hint.digital.ui.composable.screen.home.filter.shell.WorkoutFilterRowShell
import aurapixelfy.app1hint.digital.ui.state.WorkoutFilterMode

@Composable
fun WorkoutFilterRow(
    filterMode: WorkoutFilterMode,
    selectedType: WorkoutType?,
    selectedDifficulty: Difficulty?,
    onFilterModeToggled: () -> Unit,
    onTypeSelected: (WorkoutType?) -> Unit,
    onDifficultySelected: (Difficulty?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val allLabel = stringResource(R.string.axpdl_workout_filter_all)

    val items = when (filterMode) {
        WorkoutFilterMode.TYPE -> listOf(
            FilterChipItem(
                label = allLabel,
                selected = selectedType == null,
                onClick = { onTypeSelected(null) },
            ),
        ) + WorkoutType.entries.map { type ->
            FilterChipItem(
                label = stringResource(type.titleRes),
                selected = selectedType == type,
                onClick = { onTypeSelected(type) },
                icon = type.icon,
            )
        }

        WorkoutFilterMode.DIFFICULTY -> listOf(
            FilterChipItem(
                label = allLabel,
                selected = selectedDifficulty == null,
                onClick = { onDifficultySelected(null) },
            ),
        ) + Difficulty.entries.map { difficulty ->
            FilterChipItem(
                label = stringResource(difficulty.titleRes),
                selected = selectedDifficulty == difficulty,
                onClick = { onDifficultySelected(difficulty) },
                level = difficulty.level,
            )
        }
    }

    WorkoutFilterRowShell(
        filterMode = filterMode,
        onFilterModeToggled = onFilterModeToggled,
        items = items,
        modifier = modifier,
    ) { item -> FilterChipContent(item) }
}
