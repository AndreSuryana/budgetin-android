package com.andresuryana.budgetin.core.data.fake

import com.andresuryana.budgetin.core.data.repository.UserNotificationRepository
import com.andresuryana.budgetin.core.model.Notification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Calendar

class FakeUserNotificationRepository : UserNotificationRepository {

    private val notifications: MutableList<Notification> = generateNotifications()

    override fun getNotifications(): Flow<List<Notification>> =
        flowOf(notifications)

    override suspend fun updateNotificationViewedStatus(notificationId: String, viewed: Boolean) {
        try {
            val notification = this.notifications.firstOrNull { it.id == notificationId }
            if (notification != null) {
                val index = this.notifications.indexOf(notification)
                this.notifications[index] = notification.copy(viewed = true)
            } else {
                throw Exception("Notification '$notificationId' not found")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun updateAllNotificationViewedStatus(viewed: Boolean) {
        try {
            this.notifications.forEachIndexed { index, notification ->
                val newNotification = notification.copy(viewed = viewed)
                this.notifications[index] = newNotification
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun generateNotifications() = MutableList(100) { i ->
        Notification(
            id = "NTF-${i + 1}",
            title = "Notification ${i + 1}",
            description = "Lorem ipsum dolor sit amet, consectetur  sed do adipiscing elit, eiusmod tempor incididunt",
            viewed = false,
            timestamp = Calendar.getInstance().time
        )
    }
}