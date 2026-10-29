package aurapixelfy.app1hint.digital.ui.composable.screen.shared.sectiontitle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

private const val TickCount = 12
private const val LongTickEvery = 4
private val TickWidth = 2.dp
private val ShortTick = 5.dp
private val LongTick = 9.dp
private val TickSpacing = 5.dp

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Row(
            horizontalArrangement = Arrangement.spacedBy(TickSpacing),
            verticalAlignment = Alignment.Bottom,
        ) {
            repeat(TickCount) { index ->
                Box(
                    modifier = Modifier
                        .width(TickWidth)
                        .height(if (index % LongTickEvery == 0) LongTick else ShortTick)
                        .background(
                            if (index == 0) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outlineVariant
                            },
                        ),
                )
            }
        }
    }
}
