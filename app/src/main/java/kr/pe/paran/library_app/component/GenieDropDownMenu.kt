package kr.pe.paran.library_app.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun GenieDropDownMenu(
    items: List<String>,
    label: String = "",
    text: String = "",
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 2.dp),
    onValueChange: (String) -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(text) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    LaunchedEffect(key1 = text, block = {
        selectedItem = text
    })

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedItem,
            enabled = false,
            onValueChange = { /* selectedItem = it */ },
            modifier = Modifier
                .focusable(false)
                .fillMaxWidth()
                .onGloballyPositioned { coordinate ->
                    textFieldSize = coordinate.size.toSize()
                },
            label = { Text(text = if (label == selectedItem) "" else label) },
            shape = RectangleShape,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledBorderColor = Color.Gray,
                disabledLabelColor = Color.Gray,
                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                disabledTrailingIconColor = Color.Gray,
                disabledLeadingIconColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            items.forEach { value ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(value)
                        selectedItem = value
                        expanded = false
                    }
                ) {
                    Text(text = value)
                }
            }
        }
    }
}