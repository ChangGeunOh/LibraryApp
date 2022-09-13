package kr.pe.paran.library_app.screen.book_item.book_item_loan_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.component.GenieOutlinedTextField
import kr.pe.paran.library_app.component.GenieRoundedButton
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.ui.theme.Brown

@ExperimentalFoundationApi
@Composable
fun BookItemLoanContent(
    memberCard: String = "",
    bookItemList: List<BookItemData> = emptyList(),
    isCardNoValid: Boolean = false,
    onClickMemberCard: () -> Unit,
    onClickUpdate: () -> Unit = {},
    onClickBookScan: () -> Unit = {},
    onClickRemove: (BookItemData) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        GenieOutlinedTextField(label = "회원카드", text = memberCard, trailingIcon = {
            IconButton(onClick = { onClickMemberCard() }) {
                Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = "QR Scanner")
            }
        })

        Text(
            text = "대여 희망 도서",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .background(color = Brown)
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White

        )
        Box(modifier = Modifier.weight(1f)) {

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = bookItemList) {
                    BookItemLoanCard(
                        bookItemData = it,
                        onClickRemove = { bookItemData -> onClickRemove(bookItemData) })
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { onClickBookScan() }
            ) {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = "Book Scanner",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        GenieRoundedButton(
            text = "도서대여하기",
            enabled = bookItemList.isNotEmpty() && isCardNoValid
        ) {
            onClickUpdate()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun BookItemLoanCard(
    bookItemData: BookItemData,
    onClickRemove: (BookItemData) -> Unit
) {
    Column(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()
        .combinedClickable(
            onLongClick = {
                onClickRemove(bookItemData)
            },
            onClick = {}
        )
    ) {
        Text(text = bookItemData.bookData.title)
    }
}

