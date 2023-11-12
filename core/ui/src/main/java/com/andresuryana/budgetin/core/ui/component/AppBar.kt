package com.andresuryana.budgetin.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andresuryana.budgetin.core.ui.component.BudgetinAppBarDefaults.AppBarPaddingValues
import com.andresuryana.budgetin.core.ui.component.BudgetinAppBarDefaults.FixedAppBarHeight
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme

@Composable
fun BudgetinLogoAppBar(
    modifier: Modifier = Modifier
) {
    BudgetinAppBar(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        BudgetinLogoHorizontal()
    }
}

@Composable
fun BudgetinTitleAppBar(
    title: String,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    BudgetinAppBar(modifier = modifier) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        trailingIcon?.invoke()
    }
}

@Composable
fun BudgetinAppBar(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    content: @Composable RowScope.() -> Unit
) {
    BudgetinTheme {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(height = FixedAppBarHeight)
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(AppBarPaddingValues),
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement,
            content = content
        )
    }
}

@Preview
@Composable
private fun BudgetinLogoAppBarPreview() {
    BudgetinLogoAppBar()
}

@Preview
@Composable
private fun BudgetinTitleAppBarPreview() {
    BudgetinTitleAppBar(
        title = "Statistic"
    )
}

/**
 * Default properties for the Budgetin top app bar.
 */
object BudgetinAppBarDefaults {

    val AppBarPaddingValues: PaddingValues
        get() = PaddingValues(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp)

    val FixedAppBarHeight: Dp = 80.dp
}