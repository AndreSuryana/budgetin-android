package com.andresuryana.budgetin.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.andresuryana.budgetin.feature.home.navigation.homeRoute
import com.andresuryana.budgetin.feature.home.navigation.navigateToHome
import com.andresuryana.budgetin.feature.notification.navigation.navigateToNotification
import com.andresuryana.budgetin.feature.notification.navigation.notificationRoute
import com.andresuryana.budgetin.feature.search.navigation.navigateToSearch
import com.andresuryana.budgetin.feature.setting.navigation.navigateToSetting
import com.andresuryana.budgetin.feature.setting.navigation.settingRoute
import com.andresuryana.budgetin.feature.statistic.navigation.statisticRoute
import com.andresuryana.budgetin.navigation.MainDestination
import com.andresuryana.budgetin.navigation.MainDestination.HOME
import com.andresuryana.budgetin.navigation.MainDestination.NOTIFICATION
import com.andresuryana.budgetin.navigation.MainDestination.SETTING
import com.andresuryana.budgetin.navigation.MainDestination.STATISTIC

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState = remember(navController) {
    AppState(navController)
}

class AppState(val navController: NavHostController) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentMainDestination: MainDestination?
        @Composable get() = when (currentDestination?.route) {
            homeRoute -> HOME
            statisticRoute -> STATISTIC
            notificationRoute -> NOTIFICATION
            settingRoute -> SETTING
            else -> null
        }


    /**
     * Navigation logic for main destination in the application. Main destinations should
     * only have one copy of the destination in the back stack, and restore state whenever we
     * navigate to and from it.
     */
    fun navigateTo(destination: MainDestination) {
        val options = navOptions {
            // Pop up to start destination to avoid multiple stacks of destination
            // on the back stack.
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            // Avoid multiple copies of the same destination when item reselected.
            launchSingleTop = true

            // Restore state when reselecting previous selected item.
            restoreState = true
        }

        when (destination) {
            HOME -> navController.navigateToHome(options)
            STATISTIC -> navController.navigateToSearch(options)
            NOTIFICATION -> navController.navigateToNotification(options)
            SETTING -> navController.navigateToSetting(options)
        }
    }

    fun navigateToSearch() {
        navController.navigateToSearch()
    }
}