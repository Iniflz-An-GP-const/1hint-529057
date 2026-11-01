package aurapixelfy.app1hint.digital.di

import aurapixelfy.app1hint.digital.ui.viewmodel.HistoryViewModel
import aurapixelfy.app1hint.digital.ui.viewmodel.HomeViewModel
import aurapixelfy.app1hint.digital.ui.viewmodel.OnboardingViewModel
import aurapixelfy.app1hint.digital.ui.viewmodel.ProfileViewModel
import aurapixelfy.app1hint.digital.ui.viewmodel.SplashViewModel
import aurapixelfy.app1hint.digital.ui.viewmodel.StatisticsViewModel
import aurapixelfy.app1hint.digital.ui.viewmodel.WorkoutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        HomeViewModel(
            workoutRepository = get(),
            profileRepository = get(),
            workoutSessionRepository = get(),
            tipRepository = get()
        )
    }

    viewModel { (workoutId: Int) ->
        WorkoutViewModel(
            workoutId = workoutId,
            workoutRepository = get(),
            workoutSessionRepository = get()
        )
    }

    viewModel {
        HistoryViewModel(
            workoutSessionRepository = get(),
            workoutRepository = get()
        )
    }

    viewModel {
        StatisticsViewModel(
            workoutSessionRepository = get(),
            workoutRepository = get()
        )
    }

    viewModel {
        ProfileViewModel(
            profileRepository = get(),
            workoutSessionRepository = get(),
            workoutRepository = get()
        )
    }
}