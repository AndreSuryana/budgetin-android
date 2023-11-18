package com.andresuryana.budgetin.feature.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresuryana.budgetin.core.common.result.Result
import com.andresuryana.budgetin.core.common.result.asResult
import com.andresuryana.budgetin.core.data.repository.UserNotificationRepository
import com.andresuryana.budgetin.core.model.Notification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val userNotificationRepository: UserNotificationRepository
) : ViewModel() {

    val notificationUiState: StateFlow<NotificationUiState> =
        userNotificationRepository.getNotifications()
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> NotificationUiState.Success(
                        notifications = result.data
                    )

                    is Result.Error -> NotificationUiState.LoadFailed

                    is Result.Loading -> NotificationUiState.Loading
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NotificationUiState.Loading
            )

    private val _detailUiState =
        MutableStateFlow<NotificationDetailUiState>(NotificationDetailUiState.Hidden)
    val detailUiState = _detailUiState.asStateFlow()

    fun notificationViewed(notification: Notification) {
        viewModelScope.launch {
            try {
                userNotificationRepository.updateNotificationViewedStatus(
                    notificationId = notification.id,
                    viewed = true
                )
                dismissNotificationDialog()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun markAllNotificationAsViewed() {
        viewModelScope.launch {
            try {
                userNotificationRepository.updateAllNotificationViewedStatus(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showNotificationDialog(notification: Notification) {
        _detailUiState.value = NotificationDetailUiState.ShowDialog(notification)
    }

    private fun dismissNotificationDialog() {
        _detailUiState.value = NotificationDetailUiState.Hidden
    }
}