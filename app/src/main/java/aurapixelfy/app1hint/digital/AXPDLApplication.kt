package aurapixelfy.app1hint.digital

import android.app.Application
import aurapixelfy.app1hint.digital.di.dataModule
import aurapixelfy.app1hint.digital.di.dispatcherModule
import aurapixelfy.app1hint.digital.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AXPDLApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@AXPDLApplication)
            modules(appModules)
        }
    }
}
