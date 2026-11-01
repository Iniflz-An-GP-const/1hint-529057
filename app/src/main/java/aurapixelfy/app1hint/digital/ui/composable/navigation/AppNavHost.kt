package aurapixelfy.app1hint.digital.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import aurapixelfy.app1hint.digital.ui.composable.screen.history.AXPDLHistoryScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.home.AXPDLHomeScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.onboarding.AXPDLOnboardingScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.AXPDLProfileScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.settings.AXPDLSettingsScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.splash.AXPDLSplashScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.statistics.AXPDLStatisticsScreen
import aurapixelfy.app1hint.digital.ui.composable.screen.workout.AXPDLWorkoutScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    showHomeFilters: Boolean,
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

        composable<NavRoute.Home>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1hint://app/home" })
        ) {
            AXPDLHomeScreen(
                showFilters = showHomeFilters,
                onWorkoutClick = { workoutId ->
                    navController.navigate(route = NavRoute.Workout(workoutId = workoutId))
                },
            )
        }

        composable<NavRoute.Workout>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1hint://app/workout/{workoutId}" })
        ) { backStackEntry ->
            val workout: NavRoute.Workout = backStackEntry.toRoute()
            AXPDLWorkoutScreen(
                workoutId = workout.workoutId,
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable<NavRoute.History>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1hint://app/history" })
        ) {
            AXPDLHistoryScreen()
        }

        composable<NavRoute.Statistics>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1hint://app/statistics" })
        ) {
            AXPDLStatisticsScreen()
        }

        composable<NavRoute.Profile>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1hint://app/profile" })
        ) {
            AXPDLProfileScreen(
                onSettingsClick = { navController.navigate(route = NavRoute.Settings) },
            )
        }

        composable<NavRoute.Settings>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1hint://app/settings" })
        ) {
            AXPDLSettingsScreen()
        }
    }
}