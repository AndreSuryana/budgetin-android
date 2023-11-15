package com.andresuryana.budgetin.feature.notification

import com.andresuryana.budgetin.core.model.Notification

sealed interface NotificationDetailUiState {

    data object Hidden : NotificationDetailUiState

    data class ShowDialog(
        val notification: Notification
    ) : NotificationDetailUiState
}