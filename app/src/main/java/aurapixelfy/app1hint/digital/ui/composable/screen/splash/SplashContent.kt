package aurapixelfy.app1hint.digital.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val TITLE_START_DELAY_MS = 300L
private const val LOGO_DURATION_MS = 650
private const val TITLE_DURATION_MS = 500
private const val HOLD_DURATION_MS = 550L

private val LogoSize = 160.dp
private val LogoRise = 24.dp
private val TitleRise = 12.dp

@Composable
fun SplashContent(
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit,
) {
    val dimens = LocalDimens.current

    val logoProgress = remember { Animatable(0f) }
    val titleProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        coroutineScope {
            launch {
                logoProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = LOGO_DURATION_MS,
                        easing = FastOutSlowInEasing
                    ),
                )
            }
            launch {
                delay(TITLE_START_DELAY_MS.milliseconds)
                titleProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = TITLE_DURATION_MS,
                        easing = FastOutSlowInEasing
                    ),
                )
            }
        }

        delay(HOLD_DURATION_MS.milliseconds)
        onAnimationFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(48.dp))
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = stringResource(R.string.axpdl_app_logo_description),
                modifier = Modifier
                    .size(LogoSize)
                    .clip(RoundedCornerShape(40.dp))
                    .graphicsLayer {
                        alpha = logoProgress.value
                        translationY = (1f - logoProgress.value) * LogoRise.toPx()
                    },
            )

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.graphicsLayer {
                    alpha = titleProgress.value
                    translationY = (1f - titleProgress.value) * TitleRise.toPx()
                },
            )
        }
    }
}
