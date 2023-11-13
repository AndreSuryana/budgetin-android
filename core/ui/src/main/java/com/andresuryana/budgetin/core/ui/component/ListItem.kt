package com.andresuryana.budgetin.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andresuryana.budgetin.core.ui.R
import com.andresuryana.budgetin.core.ui.component.BudgetinListItemDefaults.IconContainerColor
import com.andresuryana.budgetin.core.ui.component.BudgetinListItemDefaults.IconDefault
import com.andresuryana.budgetin.core.ui.component.BudgetinListItemDefaults.ListItemCardColors
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetinListItem(
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val styledTitle: @Composable () -> Unit =
        @Composable {
            val style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            ProvideTextStyle(value = style) { title() }
        }

    val styledDescription: @Composable () -> Unit =
        @Composable {
            val style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Justify
            )
            ProvideTextStyle(value = style) { description() }
        }

    BudgetinTheme {
        Card(
            modifier = modifier,
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            colors = ListItemCardColors
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Column {
                    styledTitle()
                    Spacer(modifier = Modifier.height(4.dp))
                    styledDescription()
                }
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    trailingIcon()
                }
            }
        }
    }
}

@Composable
fun BudgetinItemIcon(
    icon: Painter,
    color: Color?,
    modifier: Modifier = Modifier,
    iconSize: IconSize = IconSize.DEFAULT,
    iconContainerColor: Color = IconContainerColor,
    iconContainerShape: Shape = RoundedCornerShape(4.dp),
) {
    BudgetinTheme {
        Box(
            modifier = modifier
                .background(
                    color = iconContainerColor,
                    shape = iconContainerShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(iconSize.value)
                    .padding(if (iconSize == IconSize.LARGE) 12.dp else 8.dp),
                painter = icon,
                contentDescription = null,
                colorFilter = if (color != null) ColorFilter.tint(color) else null
            )
        }
    }
}

@Preview
@Composable
private fun BudgetinItemIconPreview() {
    BudgetinItemIcon(
        icon = IconDefault,
        color = null
    )
}

@Preview
@Composable
private fun BudgetinListItemPreview() {
    BudgetinTheme {
        BudgetinListItem(
            title = {
                Text(text = "Item title")
            },
            description = {
                Text(text = "Lorem ipsum dolor sit amet, consectetur  sed do adipiscing elit, eiusmod tempor incididunt")
            },
            onClick = { },
            leadingIcon = {
                BudgetinItemIcon(
                    icon = IconDefault,
                    color = null
                )
            }
        )
    }
}

enum class IconSize(val value: Dp) {
    SMALL(32.dp),
    LARGE(48.dp),
    DEFAULT(SMALL.value)
}

object BudgetinListItemDefaults {

    val IconDefault: Painter
        @Composable
        get() = painterResource(R.drawable.ic_budgetin_logo)

    val IconContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)

    val ListItemCardColors: CardColors
        @Composable
        get() = CardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onBackground
        )
}