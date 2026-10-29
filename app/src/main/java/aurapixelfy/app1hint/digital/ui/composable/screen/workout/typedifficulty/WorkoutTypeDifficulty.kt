package aurapixelfy.app1hint.digital.ui.composable.screen.workout.typedifficulty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.data.model.WorkoutType
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

private val ChipIconSize = 14.dp

@Composable
fun WorkoutTypeDifficulty(
    type: WorkoutType,
    difficulty: Difficulty,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
    ) {
        InfoChip(icon = type.icon, text = stringResource(type.titleRes))
        InfoChip(icon = null, text = stringResource(difficulty.titleRes))
    }
}

@Composable
private fun InfoChip(
    icon: ImageVector?,
    text: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.controlCornerRadius),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = dimens.itemSpacing,
                vertical = dimens.tightSpacing
            ),
            horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(ChipIconSize),
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
