package kr.pe.paran.library_app.screen.book.book_insert_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.*
import kr.pe.paran.library_app.model.BookData
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_INSERT_BOOK
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.screen.book.BookViewModel
import kr.pe.paran.library_app.screen.member.member_insert_screen.ShowToast
import kr.pe.paran.library_app.utils.Logcat

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookInsertScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel()
) {

    var bookData by remember { mutableStateOf(BookData()) }

    var isShowCalendar by remember { mutableStateOf(false) }
    var isShowAuthorDialog by remember { mutableStateOf(false) }
    var isShowAddAuthor by remember { mutableStateOf(false) }

    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    val code = navController.currentBackStackEntry?.savedStateHandle?.getStateFlow("code", "")
        ?.collectAsState()
    code?.let {
        LaunchedEffect(
            key1 = it.value,
            block = {
                bookData.isbn = it.value
            }
        )
    }

    val networkState by viewModel.networkStatus.collectAsState()
    when (networkState) {
        is NetworkStatus.FAILURE -> {
            if ((networkState as NetworkStatus.FAILURE).request == REQUEST_INSERT_BOOK) {
                val message = (networkState as NetworkStatus.FAILURE).message
                if (message.contains("409")) {
                    ShowToast(message = "등록된 ISBN이 있습니다.")
                } else {
                    ShowToast(message = "등록 중 오류가 발행습니다.")
                }
            }
        }
        is NetworkStatus.SUCCESS -> {
            val request = (networkState as NetworkStatus.SUCCESS).request
            if (request == REQUEST_INSERT_BOOK) {
                ShowToast(message = "신규 서적 정보가 등록 되었습니다.")
                navController.popBackStack()
            }
        }
        else -> {}
    }

    Scaffold(topBar = {
        NavigationAppBar(
            title = "새로 나온 책 등록",
            onClickBack = { navController.popBackStack() })
    }) {
        BookInsertContent(
            bookData = bookData,
            onClickSave = {
                viewModel.insertBookData(it)
            },
            onClickCode = {
                navController.navigate(Screen.Scanner.route)
            },
            onChangeBook = { bookData = it },
            onClickDate = {
                isShowCalendar = true
            },
            onClickAuthor = {
                isShowAuthorDialog = true
            }
        )
    }

    if (isShowCalendar) {
        GenieDatePicker(onDismiss = { date ->
            date?.let {
                bookData.setDate(date = it)
            }
            isShowCalendar = false
        })
    }

    if (isShowAuthorDialog) {
        GenieAuthorDialog(
            onClickAuthor = {
                bookData.authorData = it
                isShowAuthorDialog = false
                softwareKeyboardController?.hide()
            },
            onDismissDialog = {
                isShowAuthorDialog = false
                softwareKeyboardController?.hide()
            },
            onClickAdd = {
                isShowAddAuthor = true
            }
        )
    }

    if (isShowAddAuthor) {
        GenieAuthorAddDialog(
            onClickSave = { authorData ->
                viewModel.insertAuthor(authorData)
                isShowAddAuthor = false
                softwareKeyboardController?.hide()
            },
            onDismissDialog = {
                isShowAddAuthor = false
                softwareKeyboardController?.hide()
            })
    }
}