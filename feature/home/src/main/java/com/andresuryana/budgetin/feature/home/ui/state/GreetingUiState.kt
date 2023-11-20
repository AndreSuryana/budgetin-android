package com.andresuryana.budgetin.feature.home.ui.state

import com.andresuryana.budgetin.core.model.User

sealed interface GreetingUiState {

    data object Loading : GreetingUiState

    data object LoadFailed : GreetingUiState

    data class Success(
        val user: User,
        val greetingTime: GreetingTime,
    ) : GreetingUiState

    enum class GreetingTime {
        MORNING, AFTERNOON, EVENING, NIGHT, UNSPECIFIED
    }
}