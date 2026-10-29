package aurapixelfy.app1hint.digital.ui.composable.approot.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * A hill of the map: where its top is (as a share of the screen) and two phases that give its
 * outline its own shape, so the two hills do not look like copies of each other.
 */
private data class Hill(
    val x: Float,
    val y: Float,
    val phaseA: Float,
    val phaseB: Float,
)

private val Hills = listOf(
    Hill(x = 0.98f, y = 0.1f, phaseA = 0.6f, phaseB = 2.1f),
    Hill(x = 0.02f, y = 0.92f, phaseA = 2.4f, phaseB = 0.3f),
)

private val LineWidth = 1.dp
private const val RingCount = 9

// The gap between two contour lines, as a share of the screen width.
private const val RingGapShare = 0.09f

// How far the outline wanders from a circle, and how fast the shape drifts from ring to ring.
private const val WobbleA = 0.1f
private const val WobbleB = 0.06f
private const val DriftA = 0.1f
private const val DriftB = 0.07f
private const val PointCount = 96

// Every fourth line is an index contour, drawn a little stronger like on a real map.
private const val MinorAlpha = 0.07f
private const val MajorAlpha = 0.12f
private const val IndexEvery = 4

// The outer lines fade, so the pattern dissolves into the page instead of ending abruptly.
private const val OuterFade = 0.6f

/**
 * Contour lines of a map: two hills in opposite corners, partly off the screen, with thin
 * lines around them. Static, drawn once for the screen size.
 */
@Composable
fun AppBackground(modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier
            .fillMaxSize()
            .drawWithCache {
                val gap = size.width * RingGapShare
                val stroke = Stroke(width = LineWidth.toPx())

                val lines = Hills.flatMap { hill ->
                    val center = Offset(size.width * hill.x, size.height * hill.y)
                    (1..RingCount).map { ring ->
                        val fade = 1f - OuterFade * ring / (RingCount + 1)
                        val baseAlpha = if (ring % IndexEvery == 0) MajorAlpha else MinorAlpha
                        contour(center, gap * ring, ring, hill) to baseAlpha * fade
                    }
                }

                onDrawBehind {
                    lines.forEach { (path, alpha) ->
                        drawPath(path = path, color = color.copy(alpha = alpha), style = stroke)
                    }
                }
            },
    )
}

private fun contour(center: Offset, radius: Float, ring: Int, hill: Hill): Path {
    val path = Path()

    repeat(PointCount) { index ->
        val angle = 2f * PI.toFloat() * index / PointCount
        val wobble = 1f +
                WobbleA * sin(3f * angle + hill.phaseA + ring * DriftA) +
                WobbleB * sin(5f * angle + hill.phaseB - ring * DriftB)
        val r = radius * wobble
        val x = center.x + r * cos(angle)
        val y = center.y + r * sin(angle)

        if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }

    path.close()
    return path
}
