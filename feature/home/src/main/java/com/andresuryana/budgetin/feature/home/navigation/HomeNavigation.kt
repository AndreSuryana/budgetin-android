package com.andresuryana.budgetin.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.andresuryana.budgetin.feature.home.HomeRoute

const val homeRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onSearchClicked: () -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(onSearchClicked = onSearchClicked)
    }
}