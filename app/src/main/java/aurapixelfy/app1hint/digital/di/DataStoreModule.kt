package aurapixelfy.app1hint.digital.di

import aurapixelfy.app1hint.digital.data.datastore.OnboardingDataStoreManager
import aurapixelfy.app1hint.digital.data.datastore.ProfileDataStoreManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { OnboardingDataStoreManager(androidContext()) }
    single { ProfileDataStoreManager(androidContext()) }
}