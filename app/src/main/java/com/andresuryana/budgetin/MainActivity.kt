package com.andresuryana.budgetin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme
import com.andresuryana.budgetin.ui.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetinTheme {
                MainScreen()
            }
        }
    }
}

