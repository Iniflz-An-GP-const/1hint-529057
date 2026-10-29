package aurapixelfy.app1hint.digital.di

import aurapixelfy.app1hint.digital.data.repository.OnboardingRepository
import aurapixelfy.app1hint.digital.data.repository.ProfileRepository
import aurapixelfy.app1hint.digital.data.repository.TipRepository
import aurapixelfy.app1hint.digital.data.repository.WorkoutRepository
import aurapixelfy.app1hint.digital.data.repository.WorkoutSessionRepository
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        OnboardingRepository(
            onboardingDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single { WorkoutRepository() }

    single { TipRepository() }

    single {
        WorkoutSessionRepository(
            workoutSessionDao = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single {
        ProfileRepository(
            profileDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }
}