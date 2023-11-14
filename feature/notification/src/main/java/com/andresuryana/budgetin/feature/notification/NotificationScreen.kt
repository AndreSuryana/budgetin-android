package com.andresuryana.budgetin.feature.notification

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andresuryana.budgetin.core.model.Notification
import com.andresuryana.budgetin.core.ui.component.BudgetinButton
import com.andresuryana.budgetin.core.ui.component.BudgetinDialog
import com.andresuryana.budgetin.core.ui.component.BudgetinItemIcon
import com.andresuryana.budgetin.core.ui.component.BudgetinListItem
import com.andresuryana.budgetin.core.ui.component.IconSize
import java.util.Date
import java.util.Locale

@Composable
internal fun NotificationRoute(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val notifications by viewModel.userNotifications.collectAsState()

    var showDialog by rememberSaveable { mutableStateOf(false) }

    // TODO: Refactor notification dialog
    // Should move this dialog to the NotificationScreen but maintain the logic of reading the
    // notification using view model here in the NotificationRoute
    if (showDialog)
        NotificationDetailDialog(
            notification = notifications.first(),
            onDialogClosed = {
                // TODO: Update notification read status
                Log.d("Notification", "NotificationRoute: handle notification is being read!")
                showDialog = false
            }
        )

    NotificationScreen(
        modifier = modifier,
        notifications = notifications,
        onClick = { notification ->
            Log.d("Notification", "NotificationRoute: notification=${notification.title}")
            showDialog = true
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
    onClick: (Notification) -> Unit,
    onReadAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
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

        // TODO: Refactor this into a extension function!
        // For example LazyListScope.Notifications(notifications)
        items(
            items = notifications,
            key = { it.uid }
        ) { notification ->
            BudgetinListItem(
                title = {
                    // TODO: Refactor this component because it will be used also in the dialog
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(modifier = Modifier.weight(1f), text = notification.title)
                        Text(
                            text = notification.timestamp.formatDate(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        )
                    }
                },
                description = {
                    // TODO: Refactor this component because it will be used also in the dialog
                    // Make overflow optional, because in the dialog text description is full
                    Text(
                        text = notification.description,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
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
}

@Composable
internal fun BudgetinTextButtonSmall(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .padding(4.dp),
        text = text,
        style = MaterialTheme.typography.titleSmall.copy(
            fontSize = 12.sp
        )
    )
}

@Composable
fun NotificationDetailDialog(
    notification: Notification,
    onDialogClosed: (Notification) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Need to reuse ui component after refactored
    BudgetinDialog(
        modifier = modifier,
        onDismissRequest = { onDialogClosed(notification) },
        header = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.weight(1f), text = notification.title)
                Text(
                    text = notification.timestamp.formatDate(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                )
            }
        },
        body = {
            Text(
                text = notification.description
            )
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

// TODO: Move this function into ./util/Ext.kt
private fun Date.formatDate(pattern: String = "dd MMM yyyy") =
    try {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
