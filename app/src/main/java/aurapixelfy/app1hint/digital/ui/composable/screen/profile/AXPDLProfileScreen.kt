package aurapixelfy.app1hint.digital.ui.composable.screen.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aurapixelfy.app1hint.digital.ui.composable.approot.LocalContentBottomPadding
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.sections.ProfileSections
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AXPDLProfileScreen(
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val dimens = LocalDimens.current
    val form by viewModel.form.collectAsStateWithLifecycle()
    val hasChanges by viewModel.hasChanges.collectAsStateWithLifecycle()
    val stats by viewModel.stats.collectAsStateWithLifecycle()

    ProfileSections(
        form = form,
        stats = stats,
        hasChanges = hasChanges,
        onNameChanged = viewModel::onNameChanged,
        onAgeChanged = viewModel::onAgeChanged,
        onFitnessLevelChanged = viewModel::onFitnessLevelChanged,
        onWeeklyGoalChanged = viewModel::onWeeklyGoalChanged,
        onHeightChanged = viewModel::onHeightChanged,
        onWeightChanged = viewModel::onWeightChanged,
        onSaveClick = viewModel::onSaveClick,
        onSettingsClick = onSettingsClick,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
    )
}
