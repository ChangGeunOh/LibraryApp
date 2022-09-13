package kr.pe.paran.library_app.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

@Composable
fun GenieTextField(
    label: String,
    text: String = "",
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit = { _ -> }
) {

    var value by remember { mutableStateOf(text) }
    LaunchedEffect(key1 = text, block = {value = text})

    TextField(
        value = value,
        enabled = enabled,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        label = { Text(text = label) },
        textStyle = TextStyle(textAlign = TextAlign.Center),
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if (isPassword) PasswordVisualTransformation()  else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray
        ),
        shape = RectangleShape
    )
}
