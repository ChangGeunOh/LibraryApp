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
import kr.pe.paran.library_app.model.BookItemStatusData
import kr.pe.paran.library_app.model.type.BookStatus
import kr.pe.paran.library_app.model.type.ReservationStatus
import kr.pe.paran.library_app.network.NetworkConst
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.repository.local.LocalCacheDataStore
import kr.pe.paran.library_app.screen.book.BookViewModel
import kr.pe.paran.library_app.screen.member.MemberViewModel
import kr.pe.paran.library_app.utils.Logcat

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookItemReservationScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel(),
    memberViewModel: MemberViewModel = hiltViewModel()
) {

    val networkStatus by viewModel.networkStatus.collectAsState()
    var bookItemList by remember { mutableStateOf(mutableListOf<BookItemData>()) }
    var bookItemData by remember { mutableStateOf<BookItemData?>(null) }

    val memberData by memberViewModel.memberData.collectAsState()

    when (networkStatus) {
        is NetworkStatus.SUCCESS -> {
            val request = (networkStatus as NetworkStatus.SUCCESS).request
            if (request == NetworkConst.REQUEST_SEARCH_BOOK_ITEM) {
                @Suppress("UNCHECKED_CAST")
                bookItemList = (networkStatus as NetworkStatus.SUCCESS).data as MutableList<BookItemData>
            } else if (request == NetworkConst.REQUEST_STATUS_BOOK_ITEM) {
                val bookItemData = (networkStatus as NetworkStatus.SUCCESS).data as BookItemData
                val index = bookItemList.indexOfFirst { it.id == bookItemData.id }
                if (index > -1) {
                    bookItemList.set(index = index, bookItemData)
                }
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
                val bookItemStatusData = BookItemStatusData(
                    bookItemBarCode = data.barCode,
                    memberCardCode = memberData.cardNumber,
                    bookItemStatus = if (data.status == BookStatus.Available) BookStatus.Reserved else data.status,
                    reservationStatus = if (data.status == BookStatus.Loaned) ReservationStatus.Waiting else ReservationStatus.Completed
                )
                viewModel.updateBookItemStatus(memberData.cardNumber, data.barCode)
                viewModel.updateBookItemStatus(bookItemStatusData = bookItemStatusData)
                bookItemData = null
            }
        )
    }
}