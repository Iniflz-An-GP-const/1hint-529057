package aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.content.OnboardingContentMaxWidth
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.motion.iconMotion
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.textmotion.textMotion
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

private val BandHeight = 240.dp
private val BandCorner = 32.dp
private val LeadIconBackdrop = 72.dp
private val IconBackdropStep = 10.dp
private val IconStagger = 22.dp
private const val TextWeight = 0.62f
private const val TrailEndAlpha = 0.55f
private const val BorderStartAlpha = 0.14f
private const val BorderEndAlpha = 0.4f

@Composable
fun OnboardingPageContent(
    title: String,
    description: String,
    icons: List<ImageVector>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val trailColor = MaterialTheme.colorScheme.primaryContainer
    val borderColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier
            .widthIn(max = OnboardingContentMaxWidth)
            .fillMaxWidth()
            .heightIn(min = BandHeight)
            .clip(RoundedCornerShape(BandCorner))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        trailColor.copy(alpha = 0f),
                        trailColor.copy(alpha = TrailEndAlpha),
                    ),
                ),
            )
            .border(
                width = dimens.cardBorderWidth,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        borderColor.copy(alpha = BorderStartAlpha),
                        borderColor.copy(alpha = BorderEndAlpha),
                    ),
                ),
                shape = RoundedCornerShape(BandCorner),
            ),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimens.cardPadding, vertical = dimens.cardPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(TextWeight)) {
                Text(
                    text = title,
                    modifier = Modifier.textMotion(0),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Start,
                )

                Spacer(modifier = Modifier.height(dimens.tightSpacing))

                Text(
                    text = description,
                    modifier = Modifier.textMotion(1),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                )
            }

            Column(
                modifier = Modifier.weight(1f - TextWeight),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
            ) {
                icons.forEachIndexed { index, icon ->
                    TrailIcon(
                        icon = icon,
                        backdropSize = LeadIconBackdrop - IconBackdropStep * index,
                        index = index,
                        modifier = Modifier.offset(x = -IconStagger * index),
                    )
                }
            }
        }
    }
}

@Composable
private fun TrailIcon(
    icon: ImageVector,
    backdropSize: Dp,
    index: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .iconMotion(index)
            .size(backdropSize)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(backdropSize / 2),
        )
    }
}
