package com.andresuryana.budgetin.feature.notification

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val notifications by viewModel.userNotifications.collectAsState()

    NotificationScreen(
        modifier = modifier,
        notifications = notifications,
        onNotificationShowed = { notification ->
            // TODO: Handle notification is showed, mark as read
            Toast.makeText(context, "Notification '${notification.uid}' showed!", Toast.LENGTH_SHORT).show()
        },
        onReadAllClick = {
            // TODO: Handle mark all as read
            Toast.makeText(context, "Mark all as read!", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
internal fun NotificationScreen(
    notifications: List<Notification>,
    onNotificationShowed: (Notification) -> Unit,
    onReadAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    // Define callback that responsible handling notification detail dialog that has been
    // shown to the user. This callback is reused in this composable.
    val onNotificationShowCallback: (Notification) -> Unit = { notification ->
        onNotificationShowed(notification)
        showDialog = false
    }

    if (showDialog)
        NotificationDetailDialog(
            notification = notifications.first(),
            onDialogClosed = onNotificationShowCallback
        )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
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
            onClick = { showDialog = true }
        )
    }
}

internal fun LazyListScope.loadNotifications(
    notifications: List<Notification>,
    onClick: (Notification) -> Unit
) {
    items(
        items = notifications,
        key = { it.uid }
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