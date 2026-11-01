package aurapixelfy.app1hint.digital.ui.composable.screen.home.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.data.model.WorkoutType
import aurapixelfy.app1hint.digital.ui.composable.screen.home.filter.content.FilterChipContent
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

/** Workout type chips on top, difficulty chips below; each row is single select. */
@Composable
fun WorkoutFilterRow(
    selectedType: WorkoutType?,
    selectedDifficulty: Difficulty?,
    onTypeSelected: (WorkoutType?) -> Unit,
    onDifficultySelected: (Difficulty?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val allLabel = stringResource(R.string.axpdl_workout_filter_all)

    val typeItems = listOf(
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

    val difficultyItems = listOf(
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

    Column(modifier = modifier.fillMaxWidth()) {
        ChipRow(items = typeItems, keyPrefix = "type")
        ChipRow(items = difficultyItems, keyPrefix = "difficulty")
    }
}

@Composable
private fun ChipRow(
    items: List<FilterChipItem>,
    keyPrefix: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimens.screenPadding),
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        items(items, key = { item -> "$keyPrefix-${item.label}" }) { item ->
            FilterChipContent(item)
        }
    }
}
