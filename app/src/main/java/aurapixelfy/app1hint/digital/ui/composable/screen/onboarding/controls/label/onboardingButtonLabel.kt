package aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls.label

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import aurapixelfy.app1hint.digital.R

@Composable
fun onboardingButtonLabel(
    isLastPage: Boolean,
    currentPage: Int,
    pageCount: Int,
): String {
    return stringResource(
        if (isLastPage) R.string.get_started_button_title else R.string.next_button_title
    )
}
