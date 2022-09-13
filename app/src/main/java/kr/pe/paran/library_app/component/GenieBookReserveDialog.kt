package kr.pe.paran.library_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kr.pe.paran.library_app.R
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.model.type.BookStatus
import kr.pe.paran.library_app.utils.Constants

@Preview(showBackground = true)
@ExperimentalComposeUiApi
@Composable
fun GenieBookReserveDialog(
    modifier: Modifier = Modifier
        .padding(vertical = 64.dp)
        .fillMaxSize(),
    bookItemData: BookItemData = Constants.bookItemData,
    onClickReserve: (BookItemData) -> Unit = {},
    onDismissDialog: () -> Unit = {}
) {

    Dialog(
        onDismissRequest = { onDismissDialog() },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Surface(
            modifier = Modifier.wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = bookItemData.bookData.subject,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.img_book),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Book Image"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = bookItemData.bookData.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = bookItemData.bookData.authorData.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(color = Color.LightGray.copy(alpha = 0.8f))
                        .padding(8.dp),
                    maxLines = 5,
                    text = bookItemData.bookData.authorData.description,
                    fontSize = 14.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "상태 : " + bookItemData.getStatusKor())
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = "반납예정일 : ${bookItemData.getDueDate()} ",
                        color = if (bookItemData.status == BookStatus.Loaned) Color.DarkGray else Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                GenieRoundedButton(
                    enabled = bookItemData.isAvailableReservation(),
                    text = "예약하기"
                ) {
                    onClickReserve(bookItemData)
                }
            }
        }
    }
}