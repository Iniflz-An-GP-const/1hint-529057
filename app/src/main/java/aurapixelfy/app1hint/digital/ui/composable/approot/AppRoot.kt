package aurapixelfy.app1hint.digital.ui.composable.approot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.ui.composable.approot.background.AppBackground
import aurapixelfy.app1hint.digital.ui.composable.approot.bottombar.BottomNavItem
import aurapixelfy.app1hint.digital.ui.composable.approot.bottombar.AppBottomBar
import aurapixelfy.app1hint.digital.ui.composable.approot.topbar.AppTopBar
import aurapixelfy.app1hint.digital.ui.composable.navigation.AppNavHost
import aurapixelfy.app1hint.digital.ui.composable.navigation.NavRoute
import aurapixelfy.app1hint.digital.ui.theme.Primary
import aurapixelfy.app1hint.digital.ui.theme.StatusBarIconsFor
import kotlin.reflect.KClass

private val navigationItems: List<BottomNavItem> = listOf(
    BottomNavItem(
        titleRes = R.string.axpdl_bottom_bar_nav_item_home_title,
        icon = Icons.Default.Home,
        route = NavRoute.Home
    ),
    BottomNavItem(
        titleRes = R.string.axpdl_bottom_bar_nav_item_history_title,
        icon = Icons.Default.DateRange,
        route = NavRoute.History
    ),
    BottomNavItem(
        titleRes = R.string.axpdl_bottom_bar_nav_item_statistics_title,
        icon = Icons.Default.BarChart,
        route = NavRoute.Statistics
    ),
    BottomNavItem(
        titleRes = R.string.axpdl_bottom_bar_nav_item_profile_title,
        icon = Icons.Default.AccountCircle,
        route = NavRoute.Profile
    ),
)

private val topBarHiddenScreens: List<KClass<out NavRoute>> = listOf(
    NavRoute.Splash::class,
    NavRoute.Onboarding::class,
)

private val bottomBarHiddenScreens: List<KClass<out NavRoute>> = listOf(
    NavRoute.Splash::class,
    NavRoute.Onboarding::class,
    NavRoute.Workout::class,
    NavRoute.Settings::class,
)

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val shouldShowBottomBar = !currentDestination.matchesAnyRoute(bottomBarHiddenScreens)
    val shouldShowTopBar = !currentDestination.matchesAnyRoute(topBarHiddenScreens)
    var showHomeFilters by rememberSaveable { mutableStateOf(true) }

    val onNavigateToRoute = { item: BottomNavItem ->
        val alreadyOnRoute = currentDestination.matchesAnyRoute(listOf(item.route::class))

        if (!alreadyOnRoute) {
            navController.navigate(item.route) {
                popUpTo(NavRoute.Home) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AppRootContent(
            navController = navController,
            currentDestination = currentDestination,
            shouldShowTopBar = shouldShowTopBar,
            shouldShowBottomBar = shouldShowBottomBar,
            showHomeFilters = showHomeFilters,
            onToggleHomeFilters = { showHomeFilters = !showHomeFilters },
            onNavigateToRoute = onNavigateToRoute,
            onNavigateBack = { navController.popBackStack() }
        )

        // Status bar strip, always drawn on top.
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(Primary)
        )
    }
}

@Composable
private fun AppRootContent(
    navController: NavHostController,
    currentDestination: NavDestination?,
    shouldShowTopBar: Boolean,
    shouldShowBottomBar: Boolean,
    showHomeFilters: Boolean,
    onToggleHomeFilters: () -> Unit,
    onNavigateToRoute: (BottomNavItem) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            if (shouldShowTopBar) {
                AppTopBar(
                    currentDestination = currentDestination,
                    onNavigateBack = onNavigateBack,
                    actions = {
                        if (currentDestination.matchesAnyRoute(listOf(NavRoute.Home::class))) {
                            IconButton(onClick = onToggleHomeFilters) {
                                Icon(
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = stringResource(R.string.home_filter_toggle_description),
                                    tint = if (showHomeFilters) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    },
                                )
                            }
                        }
                    },
                )
            } else {
                // No top bar here, so the status bar sits on the screen's own
                // background — the top bar variants handle the other case.
                StatusBarIconsFor(Primary)
            }
        },

        bottomBar = {
            if (shouldShowBottomBar) {
                AppBottomBar(
                    currentDestination = currentDestination,
                    navigationItems = navigationItems,
                    onNavigateToRoute = onNavigateToRoute,
                )
            }
        },

        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        val layoutDirection = LocalLayoutDirection.current

        CompositionLocalProvider(
            LocalContentBottomPadding provides paddingValues.calculateBottomPadding(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Behind every screen that does not paint its own background.
                AppBackground()

                AppNavHost(
                    navController = navController,
                    showHomeFilters = showHomeFilters,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            start = paddingValues.calculateStartPadding(layoutDirection),
                            end = paddingValues.calculateEndPadding(layoutDirection),
                        ),
                )
            }
        }
    }
}

fun NavDestination?.matchesAnyRoute(routes: List<KClass<out NavRoute>>): Boolean {
    return this?.let { destination ->
        routes.any { route -> destination.hasRoute(route) }
    } == true
}