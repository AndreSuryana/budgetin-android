package com.andresuryana.budgetin.ui

import android.icu.util.Calendar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.andresuryana.budgetin.core.ui.component.BudgetinLogoAppBar
import com.andresuryana.budgetin.core.ui.component.BudgetinNavigationBar
import com.andresuryana.budgetin.core.ui.component.BudgetinNavigationItem
import com.andresuryana.budgetin.core.ui.component.BudgetinTitleAppBar
import com.andresuryana.budgetin.core.ui.component.TimeFilterBottomSheet
import com.andresuryana.budgetin.core.ui.component.TimeFilterDropdown
import com.andresuryana.budgetin.navigation.AppNavHost
import com.andresuryana.budgetin.navigation.MainDestination
import com.andresuryana.budgetin.navigation.MainDestination.HOME
import com.andresuryana.budgetin.navigation.MainDestination.STATISTIC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: AppState = rememberAppState(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 10),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            AnimatedVisibility(
                visible = state.isShowNavigationBar,
                enter = slideInVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy
                    )
                ) { it },
                exit = slideOutVertically(
                    animationSpec = tween(
                        durationMillis = 100,
                        easing = EaseInOut
                    )
                ) { it }
            ) {
                BudgetinBottomBar(
                    destinations = state.mainDestinations,
                    onNavigate = state::navigateToMainDestination,
                    currentDestination = state.currentDestination
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Show top app bar on main destination, except HOME destination has special top app bar
            val destination = state.currentMainDestination
            if (destination != null) {
                when (destination) {
                    HOME -> BudgetinLogoAppBar()

                    STATISTIC -> {
                        BudgetinTitleAppBar(
                            title = stringResource(destination.titleRes),
                            trailingIcon = {
                                val months = listOf(
                                    "January",
                                    "February",
                                    "March",
                                    "April",
                                    "May",
                                    "June",
                                    "July",
                                    "August",
                                    "September",
                                    "October",
                                    "November",
                                    "December"
                                )
                                var selectedMonth by rememberSaveable {
                                    mutableStateOf(
                                        months[Calendar.getInstance().get(Calendar.MONTH)]
                                    )
                                }

                                var expanded by rememberSaveable { mutableStateOf(false) }

                                TimeFilterDropdown(
                                    text = selectedMonth,
                                    expanded = expanded,
                                    onExpanded = {
                                        expanded = !expanded
                                    }
                                )

                                if (expanded) {
                                    TimeFilterBottomSheet(
                                        title = "Select month",
                                        items = months,
                                        selectedItem = selectedMonth,
                                        onDismiss = { expanded = false },
                                        onSubmit = { selectedItem ->
                                            selectedMonth = selectedItem
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        )
                    }

                    else -> {
                        BudgetinTitleAppBar(
                            title = stringResource(destination.titleRes)
                        )
                    }
                }
            }

            AppNavHost(state = state)
        }
    }
}

@Composable
private fun BudgetinBottomBar(
    destinations: List<MainDestination>,
    onNavigate: (MainDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    BudgetinNavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isCurrentDestination(destination)
            BudgetinNavigationItem(
                selected = selected,
                onClick = { onNavigate(destination) },
                icon = {
                    Icon(
                        painter = painterResource(destination.unselectedIcon),
                        contentDescription = stringResource(destination.titleRes)
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(destination.selectedIcon),
                        contentDescription = stringResource(destination.titleRes)
                    )
                },
                label = {
                    Text(stringResource(destination.titleRes))
                }
            )
        }
    }
}

private fun NavDestination?.isCurrentDestination(destination: MainDestination): Boolean =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false