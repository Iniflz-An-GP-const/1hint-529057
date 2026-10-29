package aurapixelfy.app1hint.digital.ui.composable.screen.home.greeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

private val WatermarkSize = 120.dp
private val WatermarkOffset = 18.dp
private const val WatermarkAlpha = 0.18f
private const val TextAlpha = 0.85f

@Composable
fun HomeGreeting(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val gradient = Brush.horizontalGradient(
        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.tertiary),
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimens.cardCornerRadius))
            .background(gradient),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.DirectionsRun,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = WatermarkAlpha),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = WatermarkOffset)
                .size(WatermarkSize),
        )

        Column(modifier = Modifier.padding(dimens.cardPadding)) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = TextAlpha),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
