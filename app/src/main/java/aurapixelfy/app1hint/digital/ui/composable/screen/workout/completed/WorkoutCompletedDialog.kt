package aurapixelfy.app1hint.digital.ui.composable.screen.workout.completed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.typeAccentColor
import aurapixelfy.app1hint.digital.ui.composable.screen.workout.completed.SummaryStats
import aurapixelfy.app1hint.digital.ui.composable.screen.workout.completed.WorkoutSummary
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

private val WatermarkSize = 132.dp
private val WatermarkOffset = 28.dp
private const val WatermarkAlpha = 0.2f
private const val WatermarkTilt = -15f
private const val BannerTextAlpha = 0.85f

@Composable
fun WorkoutCompletedDialog(
    summary: WorkoutSummary,
    onDismiss: () -> Unit,
) {
    val dimens = LocalDimens.current
    val gradient = Brush.horizontalGradient(
        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.tertiary),
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(dimens.cardCornerRadius),
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(gradient),
                ) {
                    Icon(
                        imageVector = Icons.Filled.EmojiEvents,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = WatermarkAlpha),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(x = WatermarkOffset)
                            .size(WatermarkSize)
                            .rotate(WatermarkTilt),
                    )

                    Column(modifier = Modifier.padding(dimens.cardPadding)) {
                        Text(
                            text = stringResource(R.string.workout_finished_title),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )

                        Spacer(modifier = Modifier.height(dimens.tightSpacing))

                        Text(
                            text = stringResource(R.string.workout_finished_description),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = BannerTextAlpha),
                        )
                    }
                }

                Column(modifier = Modifier.padding(dimens.cardPadding)) {
                    Text(
                        text = summary.workoutName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Spacer(modifier = Modifier.height(dimens.tightSpacing))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = summary.typeIcon,
                            contentDescription = null,
                            tint = typeAccentColor(summary.typeOrdinal),
                            modifier = Modifier.size(18.dp),
                        )

                        Text(
                            text = summary.typeLabel,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }

                    Spacer(modifier = Modifier.height(dimens.sectionGap))

                    SummaryStats(summary = summary)

                    Spacer(modifier = Modifier.height(dimens.sectionGap))

                    Text(
                        text = stringResource(R.string.workout_completed_hint),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Spacer(modifier = Modifier.height(dimens.itemSpacing))

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(dimens.controlCornerRadius),
                    ) {
                        Text(stringResource(R.string.workout_completed_dialog_confirm))
                    }
                }
            }
        }
    }
}
