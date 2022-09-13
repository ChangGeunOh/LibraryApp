package kr.pe.paran.library_app.screen.member.member_reserved_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.screen.member.member_loaned_screen.MemberLoanedItem

@Composable
fun MemberReservedContent(
    bookItemList: List<BookItemData> = emptyList(),
    onClickReserve: () -> Unit = {},
    onClickItem: (BookItemData) -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (bookItemList.isEmpty()) {
            Text("예약한 서적이 없습니다.", modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 4.dp)
                    .fillMaxWidth()
            ) {
                items(items = bookItemList) {
                    MemberReservedItem(
                        bookItemData = it,
                        onClickItemData = { bookItemData -> onClickItem(bookItemData) }
                    )
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                onClickReserve()
            }) {
            Icon(
                imageVector = Icons.Default.BookmarkAdd,
                contentDescription = "Reserve Icon",
                tint = Color.White
            )
        }

    }
}

@Composable
fun MemberReservedItem(
    bookItemData: BookItemData,
    onClickItemData: (BookItemData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClickItemData(bookItemData) }
    ) {
        Text(text = bookItemData.bookData.title)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = bookItemData.bookData.authorData.name)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = bookItemData.reserveData.getReserveDate())
        }
    }
}