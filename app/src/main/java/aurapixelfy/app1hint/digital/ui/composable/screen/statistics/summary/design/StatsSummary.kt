package aurapixelfy.app1hint.digital.ui.composable.screen.statistics.summary.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.entity.WorkoutSessionEntity
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import java.time.LocalDate

private val HeroIconBadgeSize = 56.dp
private val SecondaryIconSize = 18.dp

@Composable
fun StatsSummary(
    sessions: List<WorkoutSessionEntity>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    val totalWorkouts = sessions.size
    val completionRate = remember(sessions) {
        if (sessions.isEmpty()) 0 else (sessions.count { it.isFullyCompleted } * 100) / sessions.size
    }

    val currentStreak = remember(sessions) {
        val sessionDates = sessions.map { it.timestamp.toLocalDate() }.toSet()
        var streak = 0
        var day = LocalDate.now()
        while (sessionDates.contains(day)) {
            streak++
            day = day.minusDays(1)
        }
        streak
    }

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(dimens.cardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(HeroIconBadgeSize)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.LocalFireDepartment,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = stringResource(R.string.statistics_summary_days_format, currentStreak),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = stringResource(R.string.statistics_summary_streak),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.contentSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        ) {
            SecondaryChip(
                icon = Icons.Filled.FitnessCenter,
                value = totalWorkouts.toString(),
                label = stringResource(R.string.statistics_summary_total),
                modifier = Modifier.weight(1f),
            )
            SecondaryChip(
                icon = Icons.Filled.CheckCircle,
                value = stringResource(R.string.statistics_summary_percent_format, completionRate),
                label = stringResource(R.string.statistics_summary_completion),
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun SecondaryChip(
    icon: ImageVector,
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(dimens.controlCornerRadius),
            )
            .padding(horizontal = dimens.itemSpacing, vertical = dimens.tightSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(SecondaryIconSize),
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
