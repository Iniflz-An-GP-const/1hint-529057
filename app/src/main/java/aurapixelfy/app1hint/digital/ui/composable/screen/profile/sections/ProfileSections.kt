package aurapixelfy.app1hint.digital.ui.composable.screen.profile.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.fields.ProfileFields
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.sectiontitle.SectionTitle
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.ProfileForm
import aurapixelfy.app1hint.digital.ui.viewmodel.ProfileStats
import java.util.Locale

private val AvatarSize = 80.dp
private val SaveButtonHeight = 52.dp

@Composable
fun ProfileSections(
    form: ProfileForm,
    stats: ProfileStats,
    hasChanges: Boolean,
    onNameChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    onFitnessLevelChanged: (Difficulty) -> Unit,
    onWeeklyGoalChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val displayName = form.name.trim().ifBlank { stringResource(R.string.profile_default_name) }

    Column(modifier = modifier) {
        ProfileHeader(
            name = displayName,
            fitnessLevel = form.fitnessLevel,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        ) {
            MiniStatCard(
                value = stats.workouts.toString(),
                label = stringResource(R.string.profile_stat_workouts),
                modifier = Modifier.weight(1f),
            )
            MiniStatCard(
                value = String.format(Locale.getDefault(), "%.1f", stats.hours),
                label = stringResource(R.string.profile_stat_hours),
                modifier = Modifier.weight(1f),
            )
            MiniStatCard(
                value = stats.daysActive.toString(),
                label = stringResource(R.string.profile_stat_days_active),
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        SectionTitle(text = stringResource(R.string.profile_screen_profile_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        ProfileFields(
            form = form,
            onNameChanged = onNameChanged,
            onAgeChanged = onAgeChanged,
            onFitnessLevelChanged = onFitnessLevelChanged,
            onWeeklyGoalChanged = onWeeklyGoalChanged,
            onHeightChanged = onHeightChanged,
            onWeightChanged = onWeightChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        AnimatedVisibility(visible = hasChanges) {
            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .padding(top = dimens.contentSpacing)
                    .fillMaxWidth()
                    .height(SaveButtonHeight),
                shape = RoundedCornerShape(dimens.controlCornerRadius),
            ) {
                Text(stringResource(R.string.profile_save_button))
            }
        }

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        SettingsLink(
            onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ProfileHeader(
    name: String,
    fitnessLevel: Difficulty,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(AvatarSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = initialsOf(name),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Surface(
            shape = RoundedCornerShape(dimens.controlCornerRadius),
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ) {
            Text(
                text = stringResource(fitnessLevel.titleRes),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(
                    horizontal = dimens.itemSpacing,
                    vertical = dimens.tightSpacing,
                ),
            )
        }
    }
}

@Composable
private fun MiniStatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(vertical = dimens.contentSpacing, horizontal = dimens.tightSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun SettingsLink(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val shape = RoundedCornerShape(dimens.cardCornerRadius)

    Row(
        modifier = modifier
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceContainer, shape)
            .clickable(onClick = onClick)
            .padding(dimens.cardPadding),
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = stringResource(R.string.profile_settings_link),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f),
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

private fun initialsOf(name: String): String {
    return name.trim()
        .split(Regex("\\s+"))
        .filter { it.isNotEmpty() }
        .take(2)
        .joinToString("") { it.first().uppercase() }
}
