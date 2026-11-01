package aurapixelfy.app1hint.digital.ui.composable.screen.home.workoutcard.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.ui.composable.screen.home.workoutcard.WorkoutCardInfo
import aurapixelfy.app1hint.digital.ui.composable.screen.workout.typedifficulty.WorkoutTypeDifficulty
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

@Composable
fun WorkoutCardContent(info: WorkoutCardInfo, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier.padding(horizontal = dimens.cardPadding, vertical = dimens.itemSpacing),
    ) {
        Text(
            text = info.name,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        WorkoutTypeDifficulty(
            type = info.type,
            difficulty = info.difficulty,
        )

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Text(
            text = stringResource(
                R.string.workout_card_meta,
                info.durationMinutes,
                info.exerciseCount,
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
