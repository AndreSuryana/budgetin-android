package com.andresuryana.budgetin.core.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andresuryana.budgetin.core.ui.component.BudgetinNavigationDefaults.navigationSelectedColor
import com.andresuryana.budgetin.core.ui.component.BudgetinNavigationDefaults.navigationSelectedIndicatorColor
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme

@Composable
fun BudgetinNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun RowScope.BudgetinNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = navigationSelectedColor(),
            selectedTextColor = navigationSelectedColor(),
            indicatorColor = navigationSelectedIndicatorColor()
        )
    )
}

@Preview
@Composable
fun BudgetinNavigationBarPreview() {
    val items = listOf("Home", "Statistic", "Notification", "Setting")
    val icons = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.List,
        Icons.Rounded.Notifications,
        Icons.Rounded.Settings
    )

    BudgetinTheme {
        BudgetinNavigationBar {
            items.forEachIndexed { index, item ->
                BudgetinNavigationItem(
                    selected = index == 0,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item
                        )
                    },
                    label = {
                        Text(text = item)
                    },
                )
            }
        }
    }
}

/**
 * Default navigation color scheme for the navigation item.
 */
object BudgetinNavigationDefaults {

    @Composable
    fun navigationSelectedColor() = MaterialTheme.colorScheme.primary

    @Composable
    fun navigationSelectedIndicatorColor() = MaterialTheme.colorScheme.surface
}