package com.andresuryana.budgetin.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.andresuryana.budgetin.core.ui.component.BudgetinDialogDefaults.DialogBackgroundColors
import com.andresuryana.budgetin.core.ui.component.BudgetinDialogDefaults.DialogPadding
import com.andresuryana.budgetin.core.ui.component.BudgetinDialogDefaults.DialogShape
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme

@Composable
internal fun BudgetinDialogBase(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    BudgetinTheme {
        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = DialogShape,
                colors = DialogBackgroundColors,
                content = content
            )
        }
    }
}

@Composable
fun BudgetinDialog(
    header: @Composable () -> Unit,
    body: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (BoxScope.() -> Unit)? = null,
    footer: @Composable (RowScope.() -> Unit)? = null,
) {
    val styledHeader: @Composable () -> Unit =
        @Composable {
            val style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            ProvideTextStyle(value = style, content = header)
        }

    val styledBody: @Composable () -> Unit =
        @Composable {
            val style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Justify
            )
            ProvideTextStyle(value = style, content = body)
        }

    BudgetinDialogBase(modifier = modifier, onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DialogPadding)
        ) {
            if (icon != null) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                    content = icon
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            styledHeader()
            Spacer(modifier = Modifier.height(12.dp))
            styledBody()

            if (footer != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = footer
                )
            }
        }
    }
}

/**
 * Default properties for the Budgetin app dialog.
 */
object BudgetinDialogDefaults {

    val DialogShape: Shape = RoundedCornerShape(16.dp)

    val DialogPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 24.dp)

    val DialogBackgroundColors: CardColors
        @Composable
        get() = CardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onBackground
        )
}