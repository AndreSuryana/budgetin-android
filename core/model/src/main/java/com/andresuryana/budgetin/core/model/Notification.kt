package com.andresuryana.budgetin.core.model

import androidx.compose.ui.graphics.painter.Painter
import java.util.Date

data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val viewed: Boolean,
    val timestamp: Date,
    val icon: Painter? = null,
)
// TODO: How can the 'icon: Painter' initialized using drawable resource name?
// Because in the database icon are stored using its resource name.