package aurapixelfy.app1hint.digital.ui.composable.approot.topbar

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.ui.composable.navigation.NavRoute
import kotlin.reflect.KClass

internal val canNavigateBackRoutes: List<KClass<out NavRoute>> = listOf(
    NavRoute.Workout::class,
    NavRoute.Settings::class,
)

internal fun topBarTitleRes(currentDestination: NavDestination?): Int? {
    return when {
        currentDestination == null -> null

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Home::class) } -> {
            R.string.app_name
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Workout::class) } -> {
            R.string.axpdl_top_bar_workout_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.History::class) } -> {
            R.string.axpdl_top_bar_history_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Statistics::class) } -> {
            R.string.axpdl_top_bar_statistics_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Profile::class) } -> {
            R.string.axpdl_top_bar_profile_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Settings::class) } -> {
            R.string.axpdl_top_bar_settings_title
        }

        else -> null
    }
}
