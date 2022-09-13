package kr.pe.paran.library_app.screen.book_item.book_item_return_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.pe.paran.library_app.component.GenieRoundedButton
import kr.pe.paran.library_app.model.BookItemData

@ExperimentalFoundationApi
@Composable
fun BookItemReturnContent(
    bookItemList: List<BookItemData>,
    onClickUpdate: () -> Unit,
    onClickScanner:() -> Unit,
    onClickRemove: (BookItemData) -> Unit = {},
) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(1f)) {

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = bookItemList) {
                    BookItemReturnCard(
                        bookItemData = it,
                        onClickRemove = { bookItemData -> onClickRemove(bookItemData) })
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { onClickScanner() }
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
            text = "도서 반납 하기",
            enabled = bookItemList.isNotEmpty()
        ) {
            onClickUpdate()
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun BookItemReturnCard(
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
