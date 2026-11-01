package aurapixelfy.app1hint.digital.ui.composable.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.ui.composable.approot.LocalContentBottomPadding
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.button.ResetDataButton
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.card.AppInfoCard
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.confirm.ResetDataConfirmDialog
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.sectiontitle.SectionTitle
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AXPDLSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val dimens = LocalDimens.current
    var showResetDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
    ) {
        SectionTitle(text = stringResource(R.string.profile_screen_about_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        AppInfoCard(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        SectionTitle(text = stringResource(R.string.profile_screen_data_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        ResetDataButton(
            onClick = { showResetDialog = true },
            modifier = Modifier.fillMaxWidth(),
        )
    }

    if (showResetDialog) {
        ResetDataConfirmDialog(
            onConfirm = {
                viewModel.onResetDataConfirmed()
                showResetDialog = false
            },
            onDismiss = { showResetDialog = false },
        )
    }
}
