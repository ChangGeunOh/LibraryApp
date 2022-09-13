package kr.pe.paran.library_app.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import kr.pe.paran.library_app.model.AuthorData
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.screen.book.BookViewModel

@ExperimentalComposeUiApi
@Composable
fun GenieAuthorAddDialog(
    modifier: Modifier = Modifier
        .padding(vertical = 64.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
    onClickSave: (AuthorData) -> Unit,
    onDismissDialog: () -> Unit,
    viewModel: BookViewModel = hiltViewModel()
) {

    val networkStatus by viewModel.networkStatus.collectAsState()
    var authorData by remember { mutableStateOf(AuthorData()) }

    if (networkStatus is NetworkStatus.SUCCESS) {
        onDismissDialog()
    }

    Dialog(
        onDismissRequest = { onDismissDialog() },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column(
            modifier = modifier
                .background(Color.White)

        ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Red)) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "작가 등록",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                GenieOutlinedTextField(
                    label = "작가명",
                    onValueChange = { authorData = authorData.copy(name = it) }
                )
                GenieOutlinedMemo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                        .height(150.dp),
                    label = "소개",
                    onValueChange = { authorData = authorData.copy(description = it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                GenieRoundedButton(
                    text = "등록하기",
                    enabled = authorData.name.isNotEmpty()
                ) {
                    onClickSave(authorData)
                }

            }
        }
    }
}
