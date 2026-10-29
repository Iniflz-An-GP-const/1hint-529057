package aurapixelfy.app1hint.digital.ui.composable.screen.home.workoutcard.shell

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.ui.composable.screen.home.workoutcard.WorkoutCardInfo
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.WorkoutImage
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.accentEdge
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.modifier.press.PressStrength
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.modifier.press.pressFeedback
import aurapixelfy.app1hint.digital.ui.composable.screen.shared.typeAccentColor
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.theme.cardBorderStroke

private val ImageHeight = 160.dp

@Composable
fun WorkoutCardShell(
    imageUrl: String,
    info: WorkoutCardInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (WorkoutCardInfo) -> Unit,
) {
    val dimens = LocalDimens.current

    val interactionSource = remember { MutableInteractionSource() }

    Card(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = modifier
            .pressFeedback(interactionSource, PressStrength.Card)
            .fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.accentEdge(typeAccentColor(info.typeOrdinal))) {
            WorkoutImage(
                imageUrl = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ImageHeight),
            )

            Column(modifier = Modifier.padding(vertical = dimens.cardPadding)) {
                content(info)
            }
        }
    }
}
