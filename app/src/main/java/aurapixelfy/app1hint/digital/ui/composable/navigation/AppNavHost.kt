package aurapixelfy.app1hint.digital.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import aurapixelfy.app1hint.digital.ui.composable.screen.history.AXPDLHistoryScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.home.AXPDLHomeScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.AXPDLOnboardingScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.AXPDLProfileScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.splash.AXPDLSplashScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.statistics.AXPDLStatisticsScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.workout.AXPDLWorkoutScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            AXPDLSplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(route = NavRoute.Onboarding) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Onboarding> {
            AXPDLOnboardingScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(NavRoute.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Home> {
            AXPDLHomeScreen(
                onWorkoutClick = { workoutId ->
                    navController.navigate(route = NavRoute.Workout(workoutId = workoutId))
                },
            )
        }

        composable<NavRoute.Workout> { backStackEntry ->
            val workout: NavRoute.Workout = backStackEntry.toRoute()
            AXPDLWorkoutScreen(
                workoutId = workout.workoutId,
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable<NavRoute.History> {
            AXPDLHistoryScreen()
        }

        composable<NavRoute.Statistics> {
            AXPDLStatisticsScreen()
        }

        composable<NavRoute.Profile> {
            AXPDLProfileScreen()
        }
    }
}