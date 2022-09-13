package kr.pe.paran.library_app.screen.login_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.pe.paran.library_app.component.GenieTextField
import kr.pe.paran.library_app.R
import kr.pe.paran.library_app.ui.theme.Brown

@Preview(showBackground = true)
@Composable
fun LoginContent(
    onClickLogin: (Boolean, String, String) -> Unit = {isMember, userid, userpw -> }
) {

    var isMember by remember { mutableStateOf(false) }
    var userid by remember { mutableStateOf("") }
    var userpw by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .padding(top = 80.dp, bottom = 80.dp)
                .size(200.dp),
            painter = painterResource(id = R.drawable.img_library_logo),
            contentDescription = "Logo"
        )

        LoginSegment(onClickItem = { value ->
            isMember = value == 0
        })

        Spacer(modifier = Modifier.height(16.dp))
        GenieTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "사용자 ID",
            onValueChange = { value -> userid = value }
        )

        Spacer(modifier = Modifier.height(16.dp))
        GenieTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "비밀번호",
            onValueChange = { value -> userpw = value }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onClickLogin(isMember, userid, userpw)
            }) {
            Text(text = "LogIn")
        }
    }

}

@Composable
fun LoginSegment(onClickItem: (Int) -> Unit) {

    var idxSelected by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        OutlinedButton(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(all = 0.dp),
            shape = RectangleShape,
            border = BorderStroke(1.dp, color = Brown),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (idxSelected == 0) Brown else Color.White,
                contentColor = if (idxSelected == 0) Color.White else Brown
            ),
            onClick = {
                idxSelected = 0
                onClickItem(0)
            }
        ) {
            Text("Member", fontWeight = FontWeight.SemiBold)
        }
        OutlinedButton(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(all = 0.dp),
            shape = RectangleShape,
            border = BorderStroke(1.dp, color = Brown),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (idxSelected == 1) Brown else Color.White,
                contentColor = if (idxSelected == 1) Color.White else Brown
            ),
            onClick = {
                idxSelected = 1
                onClickItem(1)
            }
        ) {
            Text("Librarian", fontWeight = FontWeight.SemiBold)
        }
    }
}
