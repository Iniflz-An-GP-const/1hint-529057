package aurapixelfy.app1hint.digital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import aurapixelfy.app1hint.digital.ui.composable.approot.AppRoot
import aurapixelfy.app1hint.digital.ui.theme.AXPDLAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AXPDLAppTheme {
                AppRoot()
            }
        }
    }
}