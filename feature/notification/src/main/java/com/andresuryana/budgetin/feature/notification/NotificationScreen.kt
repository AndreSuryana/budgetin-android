package com.andresuryana.budgetin.feature.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andresuryana.budgetin.core.model.Notification
import com.andresuryana.budgetin.core.ui.component.BudgetinButton
import com.andresuryana.budgetin.core.ui.component.BudgetinDialog
import com.andresuryana.budgetin.core.ui.component.BudgetinItemIcon
import com.andresuryana.budgetin.core.ui.component.BudgetinListItem
import com.andresuryana.budgetin.core.ui.component.IconSize
import com.andresuryana.budgetin.feature.notification.component.BudgetinTextButtonSmall
import com.andresuryana.budgetin.feature.notification.component.NotificationDescription
import com.andresuryana.budgetin.feature.notification.component.NotificationTitleWithDate

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

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalAlignment = Alignment.End
        ) {
            item {
                BudgetinTextButtonSmall(
                    text = stringResource(R.string.btn_mark_all_read),
                    onClick = onReadAllClick
                )
            }

            loadNotifications(
                notifications = notifications,
                onClick = onShowNotification
            )
        }
    }
}

internal fun LazyListScope.loadNotifications(
    notifications: List<Notification>,
    onClick: (Notification) -> Unit
) {
    items(
        items = notifications,
        key = { it.id }
    ) { notification ->
        BudgetinListItem(
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
                    icon = painterResource(R.drawable.ic_notification_active),
                    color = MaterialTheme.colorScheme.primary,
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