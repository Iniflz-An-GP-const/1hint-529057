package aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.controls.indicator

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val DotSize = 8.dp
private val ActiveDotWidth = 24.dp
private val DotSpacing = 8.dp

@Composable
fun OnboardingIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(DotSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageCount) { index ->
            val isActive = index == currentPage
            val width by animateDpAsState(
                targetValue = if (isActive) ActiveDotWidth else DotSize,
                label = "dotWidth",
            )
            val color by animateColorAsState(
                targetValue = if (isActive) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                },
                label = "dotColor",
            )

            Box(
                modifier = Modifier
                    .height(DotSize)
                    .width(width)
                    .background(color = color, shape = CircleShape),
            )
        }
    }
}
