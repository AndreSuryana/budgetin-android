package com.andresuryana.budgetin.feature.notification

import com.andresuryana.budgetin.core.model.Notification

sealed interface NotificationUiState {

    data object Loading : NotificationUiState

    data object Empty : NotificationUiState

    data object LoadFailed : NotificationUiState

    data class Success(
        val notifications: List<Notification> = emptyList(),
    ) : NotificationUiState
}