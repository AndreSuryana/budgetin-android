package com.andresuryana.budgetin.feature.notification

import androidx.lifecycle.ViewModel
import com.andresuryana.budgetin.core.model.Notification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor() : ViewModel() {

    val userNotifications: StateFlow<List<Notification>> =
        MutableStateFlow(
            List(100) { i ->
                Notification(
                    uid = "NTF-${i + 1}",
                    title = "Notification ${i + 1}",
                    description = "Lorem ipsum dolor sit amet, consectetur  sed do adipiscing elit, eiusmod tempor incididunt",
                    timestamp = Calendar.getInstance().time
                )
            }
        ).asStateFlow()
}