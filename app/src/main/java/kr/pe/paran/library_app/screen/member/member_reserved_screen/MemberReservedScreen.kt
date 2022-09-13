package kr.pe.paran.library_app.screen.member.member_reserved_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.screen.member.MemberViewModel

@Composable
fun MemberReservedScreen(
    navController: NavController,
    viewModel: MemberViewModel = hiltViewModel()
) {

    val bookItemList by viewModel.reservedBookItemList.collectAsState()

    MemberReservedContent(
        bookItemList = bookItemList,
        onClickReserve = {
            // 책 예약 화면으로 이동
            navController.navigate(Screen.BookItemReservation.route)
        },
        onClickItem = {
            // TODO: 예약한 책 상세 보기 ...
        }
    )
}