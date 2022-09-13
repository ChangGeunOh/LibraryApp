package kr.pe.paran.library_app.screen.book.book_insert_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.pe.paran.library_app.component.GenieDropDownMenu
import kr.pe.paran.library_app.component.GenieOutlinedTextField
import kr.pe.paran.library_app.component.GenieRoundedButton
import kr.pe.paran.library_app.model.BookData
import kr.pe.paran.model.type.BookFormat

@Preview(showBackground = true)
@Composable
fun BookInsertContent(
    bookData: BookData = BookData(),
    onClickSave: (BookData) -> Unit = {},
    onChangeBook: (BookData) -> Unit = {},
    onClickCode: () -> Unit = {},
    onClickDate: () -> Unit = {},
    onClickAuthor: () -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        item {
            GenieOutlinedTextField(
                label = "ISBN",
                onValueChange = {},
                text = bookData.isbn,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onClickCode()
                        }) {
                        Icon(
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "BarCode"
                        )
                    }
                }
            )

            GenieOutlinedTextField(
                label = "타이틀",
                onValueChange = {
                    bookData.title = it
                    onChangeBook(bookData)

                }, text = bookData.title
            )
            GenieOutlinedTextField(
                label = "주제",
                onValueChange = {
                    bookData.subject = it
                    onChangeBook(bookData)

                },
                text = bookData.subject
            )

            GenieOutlinedTextField(
                label = "작가",
                onValueChange = {
                    bookData.subject = it
                    onChangeBook(bookData)
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onClickAuthor()
                    }) {
                        Icon(imageVector = Icons.Default.Person, "Author")
                    }
                },
                text = bookData.authorData.name
            )

            GenieDropDownMenu(
                items = mutableListOf(
                    BookFormat.HardCover.toString(),
                    BookFormat.Paperback.toString(),
                    BookFormat.EBook.toString(),
                    BookFormat.AudioBook.toString()
                ),
                onValueChange = {
                    bookData.bookFormat = BookFormat.valueOf(it)
                    onChangeBook(bookData)
                },
                text = bookData.bookFormat.toString()
            )

            GenieOutlinedTextField(
                label = "언어",
                onValueChange = {
                    bookData.language = it
                    onChangeBook(bookData)
                },
                text = bookData.language
            )
            GenieOutlinedTextField(
                label = "출간일",
                onValueChange = {

                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onClickDate()
                        }) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Calendar Date"
                        )
                    }
                },
                text = bookData.getDate()
            )
            GenieOutlinedTextField(
                label = "판매정가",
                onValueChange = {
                    bookData.price = if (it.isNotEmpty()) it.toInt() else -1
                    onChangeBook(bookData)
                },
                text = if (bookData.price == -1) "" else bookData.price.toString(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            GenieRoundedButton(
                text = "등록하기",
                enabled = bookData.isNotEmpty()
            ) {
                onClickSave(bookData)
            }
        }
    }
}


/*

    var id: Int = -1,
    val isbn: String  = "",
    val title: String= "",
    val subject: String= "",
    val bookFormat: BookFormat = BookFormat.HardCover,
    var authorData: AuthorData = AuthorData(),
    val language: String = "한국어",
    val publicationDate: Long = Date().time,
    val price: Double = 0.0,
    
    onClickSearch: () -> Unit = {},
    onClickSave: (MemberData) -> Unit = {},
    memberData: MemberData = MemberData(),
    onChangValue: (MemberData) -> Unit = {}
 */