package com.andresuryana.budgetin.core.model

import android.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import java.util.Date

data class Notification(
    val uid: String,
    val title: String,
    val description: String,
    val timestamp: Date,
    val isRead: Boolean = false,
    val icon: NotificationIcon? = null,
)

data class NotificationIcon(
    val icon: Painter,
    val color: Color?,
)