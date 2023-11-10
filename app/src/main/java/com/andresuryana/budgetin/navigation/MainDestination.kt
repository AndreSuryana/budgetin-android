package com.andresuryana.budgetin.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.andresuryana.budgetin.feature.home.R as homeR
import com.andresuryana.budgetin.feature.statistic.R as statisticR
import com.andresuryana.budgetin.feature.notification.R as notificationR
import com.andresuryana.budgetin.feature.setting.R as settingR

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
        selectedIcon = homeR.drawable.ic_home_active,
        unselectedIcon = homeR.drawable.ic_home,
        titleRes = homeR.string.title_home
    ),
    STATISTIC(
        selectedIcon = statisticR.drawable.ic_statistic_active,
        unselectedIcon = statisticR.drawable.ic_statistic,
        titleRes = statisticR.string.title_statistic
    ),
    NOTIFICATION(
        selectedIcon = notificationR.drawable.ic_notification_active,
        unselectedIcon = notificationR.drawable.ic_notification,
        titleRes = notificationR.string.title_notification
    ),
    SETTING(
        selectedIcon = settingR.drawable.ic_setting_active,
        unselectedIcon = settingR.drawable.ic_setting,
        titleRes = settingR.string.title_setting
    ),
}