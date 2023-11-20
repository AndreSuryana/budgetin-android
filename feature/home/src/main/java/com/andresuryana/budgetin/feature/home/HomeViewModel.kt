package com.andresuryana.budgetin.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresuryana.budgetin.core.common.result.Result
import com.andresuryana.budgetin.core.common.result.asResult
import com.andresuryana.budgetin.core.data.repository.UserRepository
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState.GreetingTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val greetingUiState = userRepository.getUserProfile()
        .asResult()
        .map { result ->
            when (result) {
                Result.Loading -> GreetingUiState.LoadFailed
                is Result.Error -> GreetingUiState.LoadFailed
                is Result.Success -> GreetingUiState.Success(
                    user = result.data,
                    greetingTime = getGreetingTime()
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GreetingUiState.Loading
        )

    @Suppress("KotlinConstantConditions", "ConvertTwoComparisonsToRangeCheck")
    private fun getGreetingTime(): GreetingTime {
        return try {
            val calendar = Calendar.getInstance(TimeZone.getDefault())
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            when {
                hour >= 0 && hour < 12 -> GreetingTime.MORNING
                hour >= 12 && hour < 18 -> GreetingTime.AFTERNOON
                hour >= 18 && hour < 21 -> GreetingTime.EVENING
                hour >= 21 -> GreetingTime.NIGHT
                else -> GreetingTime.UNSPECIFIED
            }
        } catch (e: Exception) {
            e.printStackTrace()
            GreetingTime.UNSPECIFIED
        }
    }
}