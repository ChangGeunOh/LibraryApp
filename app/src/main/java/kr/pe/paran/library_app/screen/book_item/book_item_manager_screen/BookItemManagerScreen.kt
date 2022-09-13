package kr.pe.paran.library_app.screen.book_item.book_item_manager_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.screen.book.BookViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookItemManagerScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel()
) {

    val bookItemData by viewModel.bookItemData.collectAsState()

    val code = navController.currentBackStackEntry?.savedStateHandle?.getStateFlow("code", "")
        ?.collectAsState()
    code?.let {
        LaunchedEffect(
            key1 = it.value,
            block = {
                viewModel.getBookItem(barCode = it.value)
            }
        )
    }

    Scaffold(modifier = Modifier.fillMaxWidth(), topBar = {
        NavigationAppBar(
            title = "도서 관리",
            onClickBack = { navController.popBackStack() })
    }) {
        BookItemManagerContent(
            bookItemData = bookItemData,
            onClickScanner = {
                navController.navigate(Screen.Scanner.route)
            },
            onChangeBookItem = {
                viewModel.setBookItemData(it)
            },
            onClickUpdate = {
                viewModel.updateBookItemData(it)
            }
        )
    }
}