package kr.pe.paran.library_app.screen.book_item.book_item_loan_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.model.type.AccountStatus
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.screen.book.BookViewModel

@ExperimentalFoundationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookItemLoanScreen(navController: NavController, viewModel: BookViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val networkStatus by viewModel.networkStatus.collectAsState()

    val memberCardNumber by viewModel.memberCardNumber.collectAsState()
    val memberData by viewModel.memberData.collectAsState()
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
            title = "도서 대여",
            onClickBack = { navController.popBackStack() })
    }) {

        val isCardNoValid = memberData?.accountData?.status == AccountStatus.Active

        BookItemLoanContent(
            memberCard = memberCardNumber,
            bookItemList = bookItemList,
            isCardNoValid = isCardNoValid,
            onClickMemberCard = {
                navController.navigate(Screen.Scanner.route)
            },
            onClickRemove = {
                viewModel.removeBookItem(it)
            },
            onClickUpdate = {
                viewModel.loanBookItem(memberCard = memberCardNumber, bookItemList = bookItemList)
            },
            onClickBookScan = {
                navController.navigate(Screen.Scanner.route)
            }
        )
    }

}