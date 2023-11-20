package com.andresuryana.budgetin.feature.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andresuryana.budgetin.core.model.User
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme
import com.andresuryana.budgetin.feature.home.R
import com.andresuryana.budgetin.feature.home.ui.component.BudgetinGreetingsDefaults.GreetingImageSize
import com.andresuryana.budgetin.feature.home.ui.component.BudgetinGreetingsDefaults.GreetingTextStyle
import com.andresuryana.budgetin.feature.home.ui.component.BudgetinGreetingsDefaults.NameTextStyle
import com.andresuryana.budgetin.feature.home.ui.component.BudgetinGreetingsDefaults.greetingText
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState.GreetingTime
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState.GreetingTime.AFTERNOON
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState.GreetingTime.EVENING
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState.GreetingTime.MORNING
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState.GreetingTime.NIGHT
import com.andresuryana.budgetin.feature.home.ui.state.GreetingUiState.GreetingTime.UNSPECIFIED

@Composable
fun BudgetinGreeting(
    userName: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    greetingTime: GreetingTime = UNSPECIFIED,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    val context = LocalContext.current
    BudgetinTheme {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .error(R.drawable.ic_profile_placeholder)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_profile_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(GreetingImageSize)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = greetingText(greetingTime), style = GreetingTextStyle)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = userName, style = NameTextStyle)
            }

            if (trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetinGreetingPreview() {
    val user = User("Andre Suryana", "Andresuryana17@gmail.com", null)
    BudgetinGreeting(
        userName = user.name,
        imageUrl = user.imageUrl,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null
                )
            }
        }
    )
}

/**
 * Default properties for the BudgetinGreeting component.
 */
object BudgetinGreetingsDefaults {

    val GreetingImageSize: Dp = 42.dp

    val GreetingTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodySmall.copy(
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary
        )

    val NameTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )

    @Composable
    fun greetingText(time: GreetingTime): String = stringResource(
        when (time) {
            MORNING -> R.string.greeting_morning
            AFTERNOON -> R.string.greeting_afternoon
            EVENING -> R.string.greeting_evening
            NIGHT -> R.string.greeting_night
            UNSPECIFIED -> R.string.greeting_unspecified
        }
    )
}