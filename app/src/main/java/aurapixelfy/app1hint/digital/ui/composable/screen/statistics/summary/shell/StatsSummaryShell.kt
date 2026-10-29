package aurapixelfy.app1hint.digital.ui.composable.screen.statistics.summary.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import aurapixelfy.app1hint.digital.ui.composable.screen.statistics.summary.StatItemData
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens

@Composable
fun StatsSummaryShell(
    items: List<StatItemData>,
    modifier: Modifier = Modifier,
    content: @Composable (StatItemData) -> Unit,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        items.forEach { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(dimens.cardCornerRadius),
                    )
                    .padding(dimens.cardPadding),
            ) {
                content(item)
            }
        }
    }
}
