package com.andresuryana.budgetin.feature.notification

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andresuryana.budgetin.core.model.Notification
import com.andresuryana.budgetin.core.ui.component.BudgetinItemIcon
import com.andresuryana.budgetin.core.ui.component.BudgetinListItem
import com.andresuryana.budgetin.core.ui.component.IconSize

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
        onClick = { notification ->
            // TODO: Should show notification pop up dialog
            Toast.makeText(
                context,
                "Notification '${notification.title}' clicked!",
                Toast.LENGTH_SHORT
            ).show()
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

        items(
            items = notifications,
            key = { it.uid }
        ) { notification ->
            BudgetinListItem(
                title = { Text(text = notification.title) },
                description = {
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
        style = MaterialTheme.typography.titleSmall
    )
}