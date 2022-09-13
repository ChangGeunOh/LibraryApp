package kr.pe.paran.library_app.screen.member.member_loaned_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.screen.member.MemberViewModel

@Composable
fun MemberLoanedScreen(
    navController: NavController,
    viewModel: MemberViewModel = hiltViewModel()
) {
    val bookItemList by viewModel.loanedBookItemList.collectAsState()
    MemberLoanedContent(
        bookItemList = bookItemList,
        onClickItem = {
            // TODO: 대여 도서 상세 보기
        }
    )

}