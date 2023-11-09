package com.andresuryana.budgetin.feature.notification.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.andresuryana.budgetin.feature.notification.NotificationRoute

const val notificationRoute = "notification"

fun NavController.navigateToNotification(navOptions: NavOptions? = null) {
    this.navigate(notificationRoute, navOptions)
}

fun NavGraphBuilder.notificationScreen() {
    composable(route = notificationRoute) {
        NotificationRoute()
    }
}