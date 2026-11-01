package aurapixelfy.app1hint.digital.ui.composable.screen.statistics.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

private val ChartHeight = 140.dp
private val BarWidth = 20.dp
private val BarCornerRadius = 6.dp
private const val MinBarFraction = 0.04f

/** Seven bars, Monday to Sunday, scaled to the busiest day; an empty day keeps a thin stub. */
@Composable
fun WeeklyBarChart(
    counts: List<Int>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val maxValue = (counts.maxOrNull() ?: 0).coerceAtLeast(1)
    val locale = Locale.getDefault()

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        DayOfWeek.entries.forEachIndexed { index, day ->
            val value = counts.getOrElse(index) { 0 }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.height(dimens.tightSpacing))

                Box(
                    modifier = Modifier
                        .height(ChartHeight)
                        .width(BarWidth)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                            shape = RoundedCornerShape(BarCornerRadius),
                        ),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight((value.toFloat() / maxValue).coerceAtLeast(MinBarFraction))
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(BarCornerRadius),
                            ),
                    )
                }

                Spacer(modifier = Modifier.height(dimens.tightSpacing))

                Text(
                    text = day.getDisplayName(TextStyle.SHORT, locale),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
