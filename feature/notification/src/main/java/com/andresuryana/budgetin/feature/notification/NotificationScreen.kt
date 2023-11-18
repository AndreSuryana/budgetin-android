package com.andresuryana.budgetin.feature.notification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andresuryana.budgetin.core.model.Notification
import com.andresuryana.budgetin.core.ui.component.BudgetinButton
import com.andresuryana.budgetin.core.ui.component.BudgetinDialog
import com.andresuryana.budgetin.core.ui.component.BudgetinItemIcon
import com.andresuryana.budgetin.core.ui.component.BudgetinListItem
import com.andresuryana.budgetin.core.ui.component.IconSize
import com.andresuryana.budgetin.feature.notification.NotificationScreenDefaults.NotificationHeight
import com.andresuryana.budgetin.feature.notification.NotificationScreenDefaults.getNotificationIcon
import com.andresuryana.budgetin.feature.notification.NotificationScreenDefaults.getNotificationIconColor
import com.andresuryana.budgetin.feature.notification.component.BudgetinTextButtonSmall
import com.andresuryana.budgetin.feature.notification.component.NotificationDescription
import com.andresuryana.budgetin.feature.notification.component.NotificationTitleWithDate
import kotlinx.coroutines.launch

@Composable
internal fun NotificationRoute(
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val notificationUiState by viewModel.notificationUiState.collectAsStateWithLifecycle()
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()

    NotificationScreen(
        modifier = Modifier.fillMaxWidth(),
        onShowNotification = { viewModel.showNotificationDialog(it) },
        onNotificationShowed = { notification ->
            viewModel.notificationViewed(notification)
            viewModel.dismissNotificationDialog()
        },
        onReadAllClick = { viewModel.markAllNotificationAsViewed() },
        notificationUiState = notificationUiState,
        detailUiState = detailUiState,
    )
}

@Composable
internal fun NotificationScreen(
    onShowNotification: (Notification) -> Unit,
    onNotificationShowed: (Notification) -> Unit,
    onReadAllClick: () -> Unit,
    modifier: Modifier = Modifier,
    notificationUiState: NotificationUiState = NotificationUiState.Loading,
    detailUiState: NotificationDetailUiState = NotificationDetailUiState.Hidden
) {
    when (notificationUiState) {
        NotificationUiState.Empty -> {
            // TODO: Implement an empty screen or icon
        }

        NotificationUiState.LoadFailed -> {
            // TODO: Implement an empty screen or icon and show snackbar error
        }

        NotificationUiState.Loading -> {
            // TODO: Implement loading screen
        }

        is NotificationUiState.Success -> NotificationScreenBody(
            onShowNotification = onShowNotification,
            onNotificationShowed = onNotificationShowed,
            onReadAllClick = onReadAllClick,
            notifications = notificationUiState.notifications,
            modifier = modifier,
            detailUiState = detailUiState,
        )
    }
}

@Composable
internal fun NotificationScreenBody(
    onShowNotification: (Notification) -> Unit,
    onNotificationShowed: (Notification) -> Unit,
    onReadAllClick: () -> Unit,
    notifications: List<Notification>,
    modifier: Modifier = Modifier,
    detailUiState: NotificationDetailUiState = NotificationDetailUiState.Hidden
) {
    // Handling the detail ui state to show notification detail dialog if detailUiState
    // is ShowDialog, otherwise do nothing.
    when (detailUiState) {
        is NotificationDetailUiState.ShowDialog -> NotificationDetailDialog(
            notification = detailUiState.notification,
            onDialogClosed = onNotificationShowed
        )

        else -> Unit
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        val scope = rememberCoroutineScope()
        val density = LocalDensity.current
        val listState = rememberLazyListState()

        val notificationItemHeight = NotificationHeight
        var markReadButtonHeight by remember { mutableFloatStateOf(0f) }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalAlignment = Alignment.End,
            state = listState
        ) {
            item {
                BudgetinTextButtonSmall(
                    text = stringResource(R.string.btn_mark_all_read),
                    onClick = onReadAllClick,
                    modifier = Modifier.onGloballyPositioned {
                        markReadButtonHeight = with(density) { it.size.height.dp.toPx() }
                    }
                )
            }

            loadNotifications(
                notifications = notifications,
                onClick = onShowNotification,
                modifier = Modifier.height(height = notificationItemHeight)
            )
        }

        val showScrollTop by remember {
            // Here we specified scroll to top button visibility by checking the first visible
            // item index. Because we have 'Mark all as Read' text button as first item on the
            // list then it means index 1 is the first item on the list.
            derivedStateOf { listState.firstVisibleItemIndex > 1 }
        }
        AnimatedVisibility(
            visible = showScrollTop,
            enter = fadeIn() + scaleIn(spring(dampingRatio = Spring.DampingRatioMediumBouncy)),
            exit = fadeOut() + scaleOut(),
        ) {
            val itemSizePx = with(LocalDensity.current) { notificationItemHeight.toPx() }
            IconButton(
                onClick = {
                    scope.launch {
                        // Achieve smooth scrolling by calculating item height with item count to
                        // scrolls. Additional px are added if the first item index is even number,
                        // this is workaround solution to prevent scroll isn't go fully to top.
                        val additionalPx =
                            if (listState.firstVisibleItemIndex % 2 == 0) markReadButtonHeight
                            else 0f
                        val scrollTopByPx =
                            listState.firstVisibleItemIndex * itemSizePx + additionalPx
                        listState.animateScrollBy(
                            value = -scrollTopByPx,
                            animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
                        )
                    }
                },
                colors = IconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_up),
                    contentDescription = null,
                )
            }
        }
    }
}

internal fun LazyListScope.loadNotifications(
    notifications: List<Notification>,
    onClick: (Notification) -> Unit,
    modifier: Modifier = Modifier,
) {
    items(
        items = notifications,
        key = { it.id }
    ) { notification ->
        BudgetinListItem(
            modifier = modifier,
            title = {
                NotificationTitleWithDate(
                    modifier = Modifier.fillMaxWidth(),
                    title = notification.title,
                    date = notification.timestamp
                )
            },
            description = {
                NotificationDescription(
                    description = notification.description,
                    maxLines = 2
                )
            },
            onClick = { onClick(notification) },
            leadingIcon = {
                BudgetinItemIcon(
                    icon = getNotificationIcon(notification.viewed),
                    color = getNotificationIconColor(notification.viewed),
                    iconSize = IconSize.LARGE
                )
            }
        )
    }
}

@Composable
internal fun NotificationDetailDialog(
    notification: Notification,
    onDialogClosed: (Notification) -> Unit,
    modifier: Modifier = Modifier,
) {
    BudgetinDialog(
        modifier = modifier,
        onDismissRequest = { onDialogClosed(notification) },
        header = {
            NotificationTitleWithDate(
                modifier = Modifier.fillMaxWidth(),
                title = notification.title,
                date = notification.timestamp
            )
        },
        body = {
            NotificationDescription(description = notification.description)
        },
        footer = {
            BudgetinButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onDialogClosed(notification) }
            ) {
                Text(text = stringResource(R.string.btn_ok))
            }
        }
    )
}

/**
 * Default properties for the Notification Screen components.
 */
object NotificationScreenDefaults {

    val NotificationHeight: Dp = 76.dp

    @Composable
    fun getNotificationIcon(viewed: Boolean): Painter =
        if (viewed) painterResource(R.drawable.ic_notification)
        else painterResource(R.drawable.ic_notification_active)

    @Composable
    fun getNotificationIconColor(viewed: Boolean): Color =
        if (viewed) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.primary
}