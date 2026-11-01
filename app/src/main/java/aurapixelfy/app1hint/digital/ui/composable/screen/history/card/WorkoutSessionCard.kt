package aurapixelfy.app1hint.digital.ui.composable.screen.history.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity
import aurapixelfy.app1hint.digital.data.model.WorkoutType
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.theme.cardBorderStroke
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private val IconBadgeSize = 40.dp
private val IconSize = 20.dp

@Composable
fun WorkoutSessionCard(
    session: WorkoutSessionEntity,
    durationMinutes: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val type = remember(session.workoutType) { WorkoutType.valueOf(session.workoutType) }
    val time = remember(session.timestamp) {
        session.timestamp.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier
                .padding(dimens.cardPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(IconBadgeSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = type.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(IconSize),
                )
            }

            Spacer(modifier = Modifier.width(dimens.itemSpacing))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = session.workoutName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(dimens.tightSpacing))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(dimens.controlCornerRadius),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ) {
                        Text(
                            text = stringResource(type.titleRes),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(
                                horizontal = dimens.itemSpacing,
                                vertical = dimens.tightSpacing,
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.width(dimens.itemSpacing))

                    Text(
                        text = stringResource(R.string.workout_duration_minutes, durationMinutes),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                    )
                }
            }

            Spacer(modifier = Modifier.width(dimens.itemSpacing))

            Text(
                text = time,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
