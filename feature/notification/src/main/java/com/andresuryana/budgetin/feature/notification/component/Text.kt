package com.andresuryana.budgetin.feature.notification.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.andresuryana.budgetin.feature.notification.util.Ext.formatDate
import java.util.Date

@Composable
fun NotificationTitleWithDate(
    title: String,
    date: Date,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(modifier = Modifier.weight(1f), text = title)
        Text(
            text = date.formatDate(),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun NotificationDescription(
    description: String,
    modifier: Modifier = Modifier,
    maxLines: Int? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        modifier = modifier,
        text = description,
        maxLines = maxLines ?: Int.MAX_VALUE,
        overflow = overflow
    )
}