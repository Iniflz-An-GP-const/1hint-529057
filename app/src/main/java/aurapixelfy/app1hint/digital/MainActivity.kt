package aurapixelfy.app1hint.digital

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import aurapixelfy.app1hint.digital.ui.composable.approot.AppRoot
import aurapixelfy.app1hint.digital.ui.theme.AXPDLAppTheme
import aurapixelfy.app1hint.digital.ui.theme.Primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Primary.toArgb(), Color.BLACK)
        )
        setContent {
            AXPDLAppTheme {
                AppRoot()
            }
        }
    }
}
