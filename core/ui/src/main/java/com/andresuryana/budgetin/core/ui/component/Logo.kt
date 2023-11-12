package com.andresuryana.budgetin.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andresuryana.budgetin.core.ui.R
import com.andresuryana.budgetin.core.ui.component.BudgetinLogoDefaults.LogoSize
import com.andresuryana.budgetin.core.ui.component.BudgetinLogoDefaults.LogoTextFontWeight
import com.andresuryana.budgetin.core.ui.component.BudgetinLogoDefaults.LogoTextOffsetY
import com.andresuryana.budgetin.core.ui.component.BudgetinLogoDefaults.LogoTextStyle
import com.andresuryana.budgetin.core.ui.component.BudgetinLogoDefaults.TextGradientColors
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme

@Composable
fun BudgetinLogoHorizontal(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.app_name)
) {
    BudgetinTheme {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val gradient = Brush.horizontalGradient(TextGradientColors)
            Image(
                painter = painterResource(R.drawable.ic_budgetin_logo),
                contentDescription = null,
                modifier = Modifier.size(LogoSize)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier
                    .offset(y = LogoTextOffsetY)
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush = gradient, blendMode = BlendMode.SrcAtop)
                        }
                    },
                text = title,
                style = LogoTextStyle,
                fontWeight = LogoTextFontWeight,
            )
        }
    }
}

@Preview
@Composable
private fun BudgetinLogoHorizontalPreview() {
    BudgetinLogoHorizontal()
}

/**
 * Default properties for the Budgetin logo components.
 */
object BudgetinLogoDefaults {

    private val TextGradientStartColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant

    private val TextGradientEndColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    val TextGradientColors: List<Color>
        @Composable
        get() = listOf(TextGradientStartColor, TextGradientEndColor)

    val LogoSize: Dp = 24.dp

    // This Y offset is required to align center the text within the image logo
    val LogoTextOffsetY: Dp = (-0.5).dp

    val LogoTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleMedium

    val LogoTextFontWeight: FontWeight
        @Composable
        get() = FontWeight.SemiBold
}