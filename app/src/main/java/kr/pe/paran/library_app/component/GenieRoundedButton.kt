package kr.pe.paran.library_app.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun GenieRoundedButton(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    backgroundColor: Color = Color.Red,
    onClickButton: () -> Unit
) {
    Button(
        onClick = {
            if (enabled) {
                onClickButton()
            }
        },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(backgroundColor = if (enabled) backgroundColor else Color.LightGray),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}
