package kr.pe.paran.library_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import kr.pe.paran.library_app.R
import kr.pe.paran.library_app.model.AuthorData
import kr.pe.paran.library_app.model.BookData
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.screen.book.BookViewModel
import kr.pe.paran.library_app.utils.Logcat

@ExperimentalComposeUiApi
@Composable
fun GenieBookDialog(
    modifier: Modifier = Modifier
        .padding(vertical = 64.dp)
        .fillMaxSize(),
    onClickBook: (BookData) -> Unit,
    onClickAdd: () -> Unit,
    onDismissDialog: () -> Unit,
    viewModel: BookViewModel = hiltViewModel()
) {

    val networkStatus by viewModel.networkStatus.collectAsState()
    var bookList by remember { mutableStateOf(emptyList<BookData>()) }

    if (networkStatus is NetworkStatus.SUCCESS) {
        val dataList = (networkStatus as NetworkStatus.SUCCESS).data
        if (dataList is List<*> && dataList.isNotEmpty() && dataList.first() is BookData) {
            bookList = dataList as List<BookData>
        }
    }

    Dialog(
        onDismissRequest = { onDismissDialog() },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = modifier
        ) {
            GenieSearchView(
                onCloseClicked = {
                    onDismissDialog()
                },
                onTextChange = {
                    Logcat.i("Search:$it")
                    viewModel.searchBook(it)
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
                    .background(Color.White)
            ) {
                items(items = bookList) { authorData ->
                    BookItemView(bookData = authorData, onClickItem = { onClickBook(it) })
                }
            }

            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = {
                        onClickAdd()
                    }
                ) {
                    Icon(imageVector = Icons.Default.Book, contentDescription = "Book Author")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookItemView(
    bookData: BookData = BookData(
        title = "계속 가보겠습니다.",
        subject = "정치/경제"
    ),
    onClickItem: (BookData) -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickItem(bookData)
            }
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
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_book),
            contentDescription = "Avatar",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = bookData.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(
                text = bookData.subject,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray
            )

            Text(
                text = bookData.authorData.name,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray
            )
        }
    }
}