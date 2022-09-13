package kr.pe.paran.library_app.screen.member.member_loaned_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.pe.paran.library_app.model.BookItemData

@Composable
fun MemberLoanedContent(
    bookItemList: List<BookItemData> = emptyList(),
    onClickItem: (BookItemData) -> Unit
) {
    if (bookItemList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text("대여한 서적이 없습니다.", modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = bookItemList) {
                MemberLoanedItem(
                    bookItemData = it,
                    onClickItem = { bookItemData -> onClickItem(bookItemData) }
                )
            }
        }
    }
}

@Composable
fun MemberLoanedItem(
    bookItemData: BookItemData,
    onClickItem: (BookItemData) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClickItem(bookItemData) }
    ) {
        Text(text = bookItemData.bookData.title)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = bookItemData.getBorrowDate())
            Spacer(modifier = Modifier.weight(1f))
            Text(text = bookItemData.getDueDate())
        }
    }
}