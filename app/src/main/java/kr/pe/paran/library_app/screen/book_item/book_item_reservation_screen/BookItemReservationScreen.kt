package kr.pe.paran.library_app.screen.book_item.book_item_reservation_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.GenieBookReserveDialog
import kr.pe.paran.library_app.component.GenieSearchView
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.network.NetworkConst
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.repository.local.LocalDataStore
import kr.pe.paran.library_app.screen.book.BookViewModel
import kr.pe.paran.library_app.utils.Logcat

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookItemReservationScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel()
) {

    val networkStatus by viewModel.networkStatus.collectAsState()
    var bookItemList by remember { mutableStateOf(emptyList<BookItemData>()) }
    var bookItemData by remember { mutableStateOf<BookItemData?>(null) }

    when (networkStatus) {
        is NetworkStatus.SUCCESS -> {
            val request = (networkStatus as NetworkStatus.SUCCESS).request
            if (request == NetworkConst.REQUEST_SEARCH_BOOK_ITEM) {
                @Suppress("UNCHECKED_CAST")
                bookItemList = (networkStatus as NetworkStatus.SUCCESS).data as List<BookItemData>
            }
        }
        else -> {}
    }

    Logcat.i(bookItemList.toString())

    Scaffold(topBar = {
        GenieSearchView(
            onSearchClicked = {  },
            onTextChange = { viewModel.searchBookItem(it) },
            onCloseClicked = { navController.popBackStack() }
        )
    }) {
        BookItemReservationContent(
            bookItemList = bookItemList,
            onClickItem = { bookItemData = it }
        )
    }

    bookItemData?.let {
        GenieBookReserveDialog(
            bookItemData = it,
            onDismissDialog = {
                bookItemData = null
            },
            onClickReserve = { data ->
                // Login 정보가 필요함..
                viewModel.updateBookItemStatus(LocalDataStore.memberCardCode, data.barCode)
                bookItemData = null
            }
        )
    }
}