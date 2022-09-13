package kr.pe.paran.library_app.screen.book_item.book_item_insert_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.GenieBookDialog
import kr.pe.paran.library_app.component.GenieDatePicker
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.screen.book.BookViewModel
import kr.pe.paran.library_app.utils.Logcat
import okhttp3.internal.notify

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookItemInsertScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel()
) {

    var bookItemData by remember { mutableStateOf(BookItemData()) }
    var isShowDatePicker by remember { mutableStateOf(false) }
    var isShowBookSearch by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            NavigationAppBar(
                title = "신규 구매 도서",
                onClickBack = {
                    navController.popBackStack()
                })
        }
    ) {
        BookItemInsertContent(
            bookItemData = bookItemData,
            onClickSave = { viewModel.insertBookItem(it) },
            onChangeBookItem = {
                Logcat.i("::::${it.toString()}")
                bookItemData = it
            },
            onClickSearch = {
                isShowBookSearch = true
            },
            onClickDate = { isShowDatePicker = true }
        )
    }

    if (isShowBookSearch) {
        GenieBookDialog(
            onClickBook = {
                bookItemData.bookData = it
                isShowBookSearch = false
            },
            onClickAdd = {
                navController.navigate(Screen.BookInsert.route)
                isShowBookSearch = false
            },
            onDismissDialog = { isShowBookSearch = false }
        )
    }

    if (isShowDatePicker) {
        GenieDatePicker(
            onDismiss = { dateString ->
                dateString?.let {
                    bookItemData.setPurchaseDate(date = it)
                }
                isShowDatePicker = false
            }
        )
    }
}