package aurapixelfy.app1hint.digital.ui.composable.screen.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity
import aurapixelfy.app1hint.digital.data.model.WorkoutType
import aurapixelfy.app1hint.digital.ui.composable.approot.LocalContentBottomPadding
import aurapixelfy.app1hint.digital.ui.composable.screen.statistics.chart.WeeklyBarChart
import aurapixelfy.app1hint.digital.ui.state.DataUiState
import aurapixelfy.app1hint.digital.ui.state.weeklyCounts
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.StatisticsViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

private data class StatCard(val value: String, val label: String)

@Composable
fun AXPDLStatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = koinViewModel(),
) {
    val sessionsState by viewModel.sessionsState.collectAsStateWithLifecycle()

    val sessions =
        (sessionsState as? DataUiState.Populated<List<WorkoutSessionEntity>>)?.data.orEmpty()
    val totalMinutes = remember(sessions) { viewModel.totalMinutes(sessions) }

    StatisticsContent(
        sessions = sessions,
        totalMinutes = totalMinutes,
        modifier = modifier,
    )
}

@Composable
private fun StatisticsContent(
    sessions: List<WorkoutSessionEntity>,
    totalMinutes: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    val weeklyCounts = remember(sessions) { sessions.weeklyCounts() }
    val favoriteType = remember(sessions) {
        sessions
            .groupingBy { it.workoutType }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key
            ?.let { name -> WorkoutType.entries.firstOrNull { it.name == name } }
    }

    val cards = listOf(
        StatCard(
            value = sessions.size.toString(),
            label = stringResource(R.string.statistics_total_workouts),
        ),
        StatCard(
            value = String.format(Locale.getDefault(), "%.1f", totalMinutes / 60f),
            label = stringResource(R.string.statistics_total_hours),
        ),
        StatCard(
            value = favoriteType?.let { stringResource(it.titleRes) }
                ?: stringResource(R.string.statistics_favorite_none),
            label = stringResource(R.string.statistics_favorite_type),
        ),
        StatCard(
            value = stringResource(
                R.string.statistics_weekly_activity_format,
                weeklyCounts.count { it > 0 },
            ),
            label = stringResource(R.string.statistics_weekly_activity),
        ),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = dimens.screenPadding,
            end = dimens.screenPadding,
            top = dimens.screenPadding,
            bottom = dimens.screenPadding + LocalContentBottomPadding.current,
        ),
        horizontalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
    ) {
        items(cards) { card ->
            StatCardItem(card = card)
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            SectionCard {
                Text(
                    text = stringResource(R.string.statistics_weekly_chart_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(dimens.contentSpacing))

                WeeklyBarChart(counts = weeklyCounts)
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            SectionCard {
                Text(
                    text = stringResource(R.string.statistics_motivation_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )

                Spacer(modifier = Modifier.height(dimens.tightSpacing))

                Text(
                    text = stringResource(
                        if (sessions.isEmpty()) {
                            R.string.statistics_motivation_start
                        } else {
                            R.string.statistics_motivation_progress
                        }
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun StatCardItem(card: StatCard, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(dimens.cardPadding),
    ) {
        Text(
            text = card.value,
            fontSize = 28.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = card.label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun SectionCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(dimens.cardPadding),
    ) {
        content()
    }
}
