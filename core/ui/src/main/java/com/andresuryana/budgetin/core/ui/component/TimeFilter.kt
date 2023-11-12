package com.andresuryana.budgetin.core.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andresuryana.budgetin.core.ui.R
import com.andresuryana.budgetin.core.ui.component.TimeFilterDefaults.BackgroundColor
import com.andresuryana.budgetin.core.ui.component.TimeFilterDefaults.DropdownHeight
import com.andresuryana.budgetin.core.ui.component.TimeFilterDefaults.DropdownShape
import com.andresuryana.budgetin.core.ui.component.TimeFilterDefaults.DropdownTextFontWeight
import com.andresuryana.budgetin.core.ui.component.TimeFilterDefaults.DropdownTextStyle
import com.andresuryana.budgetin.core.ui.theme.BudgetinTheme
import kotlinx.coroutines.launch

@Composable
fun TimeFilterDropdown(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    expanded: Boolean = false,
    onExpanded: () -> Unit
) {
    val animatedAngle by animateFloatAsState(
        // Target value when state is expanded is set to negative to achieve
        // clockwise rotation direction.
        targetValue = if (expanded) -180f else 0f,
        animationSpec = tween(200),
        label = "DropdownIconRotationAnimation"
    )

    Row(
        modifier = modifier
            .height(DropdownHeight)
            .clip(DropdownShape)
            .background(BackgroundColor)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                onClick = onExpanded
            )
            // This padding aren't symmetric because the Icon already has default padding.
            .padding(start = 8.dp, end = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = DropdownTextStyle,
            fontWeight = DropdownTextFontWeight
        )
        Icon(
            modifier = Modifier.rotate(animatedAngle),
            imageVector = Icons.Rounded.KeyboardArrowDown,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeFilterBottomSheet(
    items: List<String>,
    selectedItem: String,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    submitText: String = stringResource(R.string.btn_submit),
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
) {
    BudgetinTheme {
        val scope = rememberCoroutineScope()
        var selectedOption by rememberSaveable { mutableStateOf(selectedItem) }

        ModalBottomSheet(
            modifier = modifier,
            onDismissRequest = onDismiss,
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(horizontal = 16.dp)
            ) {
                title?.let {
                    Text(text = it, style = MaterialTheme.typography.titleLarge)
                }
                items.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .selectable(
                                selected = item == selectedOption,
                                onClick = { selectedOption = item },
                                role = Role.RadioButton
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = item == selectedOption, onClick = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = item, style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion {
                                onSubmit(selectedOption)
                            }
                    }
                ) {
                    Text(text = submitText)
                }
            }
        }
    }
}

/**
 * Default properties for the TimeFilterDropdown.
 */
object TimeFilterDefaults {

    val DropdownHeight: Dp = 32.dp

    val DropdownShape: Shape = RoundedCornerShape(4.dp)

    val BackgroundColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)

    val DropdownTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleSmall

    val DropdownTextFontWeight: FontWeight
        @Composable
        get() = FontWeight.Medium
}