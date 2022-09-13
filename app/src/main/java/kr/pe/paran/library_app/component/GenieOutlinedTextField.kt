package kr.pe.paran.library_app.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Stable
@Composable
fun GenieOutlinedTextField(
    label: String,
    text: String = "",
    enabled: Boolean = true,
    modifier: Modifier = Modifier.padding(vertical = 2.dp),
    shape: Shape =  RectangleShape,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = { _ -> },
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    var value by remember { mutableStateOf(text) }
    LaunchedEffect(key1 = text, block = {
        value = text
    } )

    OutlinedTextField(
        value = if (enabled) value else text,
        enabled = enabled,
        singleLine = true,
        onValueChange = {
            value = it
            if (enabled) {
                onValueChange(it)
            }
        },
        label = { Text(text = label) },
        textStyle = TextStyle(textAlign = TextAlign.Center),
        modifier = modifier
            .fillMaxWidth()
        ,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        shape = shape,
        trailingIcon = trailingIcon,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        keyboardActions = keyboardActions
    )
}
