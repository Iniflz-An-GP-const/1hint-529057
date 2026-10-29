package aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls.shell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

@Composable
fun OnboardingControlsShell(
    pageCount: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    indicator: @Composable (pageCount: Int, currentPage: Int, modifier: Modifier) -> Unit,
    buttonLabel: @Composable (isLastPage: Boolean, currentPage: Int, pageCount: Int) -> String,
    button: @Composable (label: String, onClick: () -> Unit, modifier: Modifier) -> Unit,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            indicator(pageCount, currentPage, Modifier)

            TextButton(
                onClick = onSkipClick,
                enabled = !isLastPage,
                modifier = Modifier.alpha(if (isLastPage) 0f else 1f),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                ),
            ) {
                Text(text = stringResource(R.string.skip_button_title))
            }
        }

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        button(
            buttonLabel(isLastPage, currentPage, pageCount),
            onNextClick,
            Modifier.fillMaxWidth(),
        )
    }
}
