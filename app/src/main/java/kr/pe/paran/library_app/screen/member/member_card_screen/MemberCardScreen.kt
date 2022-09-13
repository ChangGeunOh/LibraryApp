package kr.pe.paran.library_app.screen.member.member_card_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.NavigationAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MemberCardScreen(
    navController: NavController,
    viewModel: MemberCardViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            NavigationAppBar(title = "도서관 회원증", onClickBack = { navController.popBackStack() })
        }
    ) {
        MemberCardContent(viewModel.memberData)
    }
}

