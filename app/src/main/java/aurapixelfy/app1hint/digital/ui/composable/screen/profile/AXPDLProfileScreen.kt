package aurapixelfy.app1hint.digital.ui.composable.screen.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aurapixelfy.app1hint.digital.ui.composable.approot.LocalContentBottomPadding
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.confirm.ResetDataConfirmDialog
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.sections.ProfileSections
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AXPDLProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val weeklyGoalText by viewModel.weeklyGoalText.collectAsStateWithLifecycle()
    val heightText by viewModel.heightText.collectAsStateWithLifecycle()
    val weightText by viewModel.weightText.collectAsStateWithLifecycle()

    ProfileContent(
        weeklyGoalText = weeklyGoalText,
        heightText = heightText,
        weightText = weightText,
        onWeeklyGoalChanged = viewModel::onWeeklyGoalChanged,
        onHeightChanged = viewModel::onHeightChanged,
        onWeightChanged = viewModel::onWeightChanged,
        onResetDataConfirmed = viewModel::onResetDataConfirmed,
        modifier = modifier,
    )
}

@Composable
private fun ProfileContent(
    weeklyGoalText: String,
    heightText: String,
    weightText: String,
    onWeeklyGoalChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onResetDataConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    var showResetDialog by remember { mutableStateOf(false) }

    ProfileSections(
        weeklyGoalText = weeklyGoalText,
        heightText = heightText,
        weightText = weightText,
        onWeeklyGoalChanged = onWeeklyGoalChanged,
        onHeightChanged = onHeightChanged,
        onWeightChanged = onWeightChanged,
        onResetClick = { showResetDialog = true },
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

    if (showResetDialog) {
        ResetDataConfirmDialog(
            onConfirm = {
                onResetDataConfirmed()
                showResetDialog = false
            },
            onDismiss = { showResetDialog = false },
        )
    }
}
