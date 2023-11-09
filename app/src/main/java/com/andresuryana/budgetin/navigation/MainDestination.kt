package com.andresuryana.budgetin.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Type for the main (root) level destinations in the application. Each of these
 * destinations indicating items in the application bottom bar.
 */
enum class MainDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val titleRes: Int
) {
    HOME(
        selectedIcon = 0,
        unselectedIcon = 0,
        titleRes = 0
    ),
    STATISTIC(
        selectedIcon = 0,
        unselectedIcon = 0,
        titleRes = 0
    ),
    NOTIFICATION(
        selectedIcon = 0,
        unselectedIcon = 0,
        titleRes = 0
    ),
    SETTING(
        selectedIcon = 0,
        unselectedIcon = 0,
        titleRes = 0
    ),
}