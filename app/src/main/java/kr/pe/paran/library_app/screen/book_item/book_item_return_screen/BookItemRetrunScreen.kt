package kr.pe.paran.library_app.screen.book_item.book_item_return_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.screen.book.BookViewModel

@ExperimentalFoundationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookItemReturnScreen(
    navController: NavController,
    viewModel: BookViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val memberCardNumber by viewModel.memberCardNumber.collectAsState()
    val bookItemList by viewModel.bookItemList.collectAsState()

    val message by viewModel.message.collectAsState()
    if (message.isNotEmpty()) {
        LaunchedEffect(key1 = message, block = {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })
    }

    // Scan 한 후 전달받은 BarCode
    navController.currentBackStackEntry?.savedStateHandle?.getStateFlow("code", "")
        ?.collectAsState()?.let { barCode ->
            LaunchedEffect(
                key1 = barCode.value,
                block = {
                    if (barCode.value.startsWith("M")) {
                        viewModel.setMemberCardNumber(barCode.value)
                        viewModel.getMemberData(cardNo = barCode.value)
                    } else {
                        viewModel.appendBookItem(barCode = barCode.value)
                    }
                }
            )
        }

    Scaffold(topBar = {
        NavigationAppBar(
            title = "도서 반납",
            onClickBack = { navController.popBackStack() })
    }) {

        BookItemReturnContent(
            bookItemList = bookItemList,
            onClickRemove = {
                viewModel.removeBookItem(it)
            },
            onClickUpdate = {
                viewModel.returnBookItem(memberCard = memberCardNumber, bookItemList = bookItemList)
            },
            onClickScanner = {
                navController.navigate(Screen.Scanner.route)
            }
        )
    }
}