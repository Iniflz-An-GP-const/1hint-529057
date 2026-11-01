package aurapixelfy.app1hint.digital.ui.composable.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity
import aurapixelfy.app1hint.digital.ui.composable.approot.LocalContentBottomPadding
import aurapixelfy.app1hint.digital.ui.composable.screen.history.card.WorkoutSessionCard
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.DataEmptyContent
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.listmotion.LocalPlayedEntrances
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.listmotion.listEntrance
import aurapixelfy.app1hint.digital.ui.state.DataUiState
import aurapixelfy.app1hint.digital.ui.state.currentStreak
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.HistoryViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun AXPDLHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = koinViewModel(),
) {
    val sessionsState by viewModel.sessionsState.collectAsStateWithLifecycle()

    val sessions =
        (sessionsState as? DataUiState.Populated<List<WorkoutSessionEntity>>)?.data.orEmpty()

    HistoryContent(
        sessions = sessions,
        durationMinutes = viewModel::durationMinutes,
        modifier = modifier,
    )
}

@Composable
private fun HistoryContent(
    sessions: List<WorkoutSessionEntity>,
    durationMinutes: (Int) -> Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    val playedEntrances = remember { mutableSetOf<Any>() }
    val sessionsByDate = remember(sessions) {
        sessions
            .sortedByDescending { it.timestamp }
            .groupBy { it.timestamp.toLocalDate() }
    }
    val streak = remember(sessions) { sessions.currentStreak() }

    CompositionLocalProvider(LocalPlayedEntrances provides playedEntrances) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
            verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        ) {
            item {
                HistorySummaryCard(
                    totalWorkouts = sessions.size,
                    streak = streak,
                )
            }

            if (sessions.isEmpty()) {
                item {
                    DataEmptyContent(
                        primaryText = stringResource(R.string.history_empty_title),
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight(0.6f),
                    )
                }
            } else {
                sessionsByDate.forEach { (date, daySessions) ->
                    item(key = "header-$date") {
                        Text(
                            text = friendlyDate(date),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = dimens.itemSpacing),
                        )
                    }

                    items(daySessions, key = { session -> session.id }) { session ->
                        WorkoutSessionCard(
                            session = session,
                            durationMinutes = durationMinutes(session.workoutId),
                            modifier = Modifier.listEntrance(key = session.id, index = 0),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HistorySummaryCard(
    totalWorkouts: Int,
    streak: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(colors.primary, colors.primary.copy(alpha = 0.75f)),
                ),
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(dimens.cardPadding),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        SummaryValue(
            value = totalWorkouts.toString(),
            label = stringResource(R.string.history_summary_total),
            modifier = Modifier.weight(1f),
        )
        SummaryValue(
            value = streak.toString(),
            label = stringResource(R.string.statistics_summary_streak),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SummaryValue(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
private fun friendlyDate(date: LocalDate): String {
    val today = LocalDate.now()
    return when (date) {
        today -> stringResource(R.string.history_today)
        today.minusDays(1) -> stringResource(R.string.history_yesterday)
        else -> date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }
}
