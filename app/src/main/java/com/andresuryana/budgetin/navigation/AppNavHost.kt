package com.andresuryana.budgetin.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.andresuryana.budgetin.feature.home.navigation.homeRoute
import com.andresuryana.budgetin.feature.home.navigation.homeScreen
import com.andresuryana.budgetin.feature.notification.navigation.notificationScreen
import com.andresuryana.budgetin.feature.search.navigation.searchScreen
import com.andresuryana.budgetin.feature.setting.navigation.settingScreen
import com.andresuryana.budgetin.feature.statistic.navigation.statisticScreen
import com.andresuryana.budgetin.ui.AppState

@Composable
fun AppNavHost(
    state: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute
) {
    val navController = state.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) }
    ) {
        homeScreen(
            onSearchClick = state::navigateToSearch
        )
        statisticScreen()
        notificationScreen()
        settingScreen()
        searchScreen()
    }
}