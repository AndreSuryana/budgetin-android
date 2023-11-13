package com.andresuryana.budgetin.core.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.andresuryana.budgetin.core.ui.component.BudgetinButtonDefaults.ButtonShape
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme

@Composable
fun BudgetinButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    BudgetinTheme {
        Button(
            modifier = modifier,
            onClick = onClick,
            content = content,
            enabled = enabled,
            shape = ButtonShape
        )
    }
}

/**
 * Default properties for the Budgetin Button.
 */
object BudgetinButtonDefaults {

    val ButtonShape: CornerBasedShape
        @Composable
        get() = MaterialTheme.shapes.small
}