package aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.textmotion.textMotion
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.WorkoutImage
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

private val ImageCorner = 32.dp
private const val ImageHeightFraction = 0.5f

/** A stock photo on the top half of the page, the title and the description below it. */
@Composable
fun OnboardingPageContent(
    title: String,
    description: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WorkoutImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .widthIn(max = OnboardingContentMaxWidth)
                .fillMaxWidth()
                .fillMaxHeight(ImageHeightFraction)
                .clip(RoundedCornerShape(ImageCorner)),
        )

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        Column(
            modifier = Modifier
                .widthIn(max = OnboardingContentMaxWidth)
                .fillMaxWidth(),
        ) {
            Text(
                text = title,
                modifier = Modifier.textMotion(0),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 22.sp,
                textAlign = TextAlign.Start,
            )

            Spacer(modifier = Modifier.height(dimens.itemSpacing))

            Text(
                text = description,
                modifier = Modifier.textMotion(1),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
            )
        }
    }
}
