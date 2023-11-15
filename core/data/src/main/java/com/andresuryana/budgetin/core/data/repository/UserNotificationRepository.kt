package com.andresuryana.budgetin.core.data.repository

import com.andresuryana.budgetin.core.model.Notification
import kotlinx.coroutines.flow.Flow

interface UserNotificationRepository {

    /**
     * Get the user's notifications
     */
    fun getNotifications(): Flow<List<Notification>>

    /**
     * Updates the user's notification viewed status by notification id
     */
    suspend fun updateNotificationViewedStatus(notificationId: String, viewed: Boolean)

    /**
     * Updates all of the user's notification viewed status
     */
    suspend fun updateAllNotificationViewedStatus(viewed: Boolean)
}