package aurapixelfy.app1hint.digital.ui.composable.screen.shared.pulse

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.pulse.PulseTiming

private const val HALO_START_ALPHA = 0.5f
private val HaloReach = 10.dp
private val HaloWidth = 3.dp

@Composable
fun Modifier.pulseEffect(cornerRadius: Dp, timing: PulseTiming): Modifier {
    if (timing.isOff) return this

    val color = MaterialTheme.colorScheme.primary
    val progress = rememberInfiniteTransition(label = "actionHalo").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = timing.cycleMs
                0f at 0
                1f at timing.durationMs using LinearOutSlowInEasing
                if (timing.pauseMs > 0) 1f at timing.cycleMs
            },
            repeatMode = RepeatMode.Restart,
        ),
        label = "actionHaloProgress",
    )

    return drawBehind {
        val value = progress.value
        val reach = HaloReach.toPx() * timing.intensity * value
        val radius = cornerRadius.toPx() + reach
        val alpha = HALO_START_ALPHA * timing.intensity.coerceAtMost(1f) * (1f - value)

        drawRoundRect(
            color = color.copy(alpha = alpha),
            topLeft = Offset(-reach, -reach),
            size = Size(size.width + 2 * reach, size.height + 2 * reach),
            cornerRadius = CornerRadius(radius, radius),
            style = Stroke(width = HaloWidth.toPx()),
        )
    }
}
