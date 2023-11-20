package com.andresuryana.budgetin.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andresuryana.budgetin.feature.home.ui.component.BudgetinGreeting
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState

@Composable
internal fun HomeRoute(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val greetingUiState by viewModel.greetingUiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        onSearchClick = onSearchClick,
        greetingUiState = greetingUiState,
    )
}

@Composable
internal fun HomeScreen(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    greetingUiState: GreetingUiState = GreetingUiState.Loading,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        loadGreetings(
            modifier = Modifier.fillMaxWidth(),
            greetingUiState = greetingUiState,
            searchButton = {
                IconButton(
                    modifier = Modifier.offset(x = 8.dp),
                    onClick = onSearchClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }
        )
    }
}

internal fun LazyListScope.loadGreetings(
    searchButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    greetingUiState: GreetingUiState = GreetingUiState.Loading,
) {
    item {
        when (greetingUiState) {
            GreetingUiState.LoadFailed -> {
                // TODO: Need implementation
            }

            GreetingUiState.Loading -> {
                // TODO: Need implementation
            }

            is GreetingUiState.Success -> BudgetinGreeting(
                modifier = modifier,
                userName = greetingUiState.user.name,
                imageUrl = greetingUiState.user.imageUrl,
                greetingTime = greetingUiState.greetingTime,
                trailingIcon = searchButton
            )
        }
    }
}