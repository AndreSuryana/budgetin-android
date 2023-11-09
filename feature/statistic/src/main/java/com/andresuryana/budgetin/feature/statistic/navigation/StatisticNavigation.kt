package com.andresuryana.budgetin.feature.statistic.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.andresuryana.budgetin.feature.statistic.StatisticRoute

const val statisticRoute = "statistic"

fun NavController.navigateToStatistic(navOptions: NavOptions? = null) {
    this.navigate(statisticRoute, navOptions)
}

fun NavGraphBuilder.statisticScreen() {
    composable(route = statisticRoute) {
        StatisticRoute()
    }
}