package aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls.button.OnboardingButton
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls.indicator.OnboardingIndicator
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls.label.onboardingButtonLabel
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls.shell.OnboardingControlsShell
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.pulse.actionPulse
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

@Composable
fun OnboardingControls(
    pageCount: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    OnboardingControlsShell(
        pageCount = pageCount,
        currentPage = currentPage,
        isLastPage = isLastPage,
        onSkipClick = onSkipClick,
        onNextClick = onNextClick,
        modifier = modifier,
        indicator = { pc, cp, indicatorModifier -> OnboardingIndicator(pc, cp, indicatorModifier) },
        buttonLabel = { last, cp, pc -> onboardingButtonLabel(last, cp, pc) },
        button = { label, onClick, buttonModifier ->
            OnboardingButton(
                label,
                onClick,
                buttonModifier.actionPulse(dimens.controlCornerRadius, enabled = isLastPage),
            )
        },
    )
}
