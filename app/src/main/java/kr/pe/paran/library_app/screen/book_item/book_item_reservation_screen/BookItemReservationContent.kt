package kr.pe.paran.library_app.screen.book_item.book_item_reservation_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.R
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.utils.Constants

@Composable
fun BookItemReservationContent(
    bookItemList: List<BookItemData> = emptyList(),
    onClickItem: (BookItemData) -> Unit = {}
) {
    LazyColumn {
        items(items = bookItemList) {bookItemData ->
            BookItemReservationCard(
                bookItemData = bookItemData,
                onClickItem = { onClickItem(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemReservationCard(
    bookItemData: BookItemData = Constants.bookItemData,
    onClickItem: (BookItemData) -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = bookItemData.isAvailableReservation(), onClick = { onClickItem(bookItemData) })
            .drawBehind {
                val strokeWidth = 1f
                val y = size.height - strokeWidth
                drawLine(
                    color = Color.LightGray,
                    Offset(x = 62f, y = y),
                    Offset(x = size.width - 62f, y),
                    strokeWidth = strokeWidth
                )
            }
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.img_book),
            contentDescription = "Book Icon"
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(text = bookItemData.bookData.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = bookItemData.bookData.subject, fontSize = 12.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = bookItemData.bookData.authorData.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "상태 : " + bookItemData.getStatusKor())
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "대여가능",
                    color = if (bookItemData.isAvailableReservation()) Color.Red else Color.LightGray
                )
            }
        }
    }
}