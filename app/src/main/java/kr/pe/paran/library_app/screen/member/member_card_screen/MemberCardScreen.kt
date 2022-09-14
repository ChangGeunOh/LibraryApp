package kr.pe.paran.library_app.screen.member.member_card_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.screen.member.MemberViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MemberCardScreen(
    navController: NavController,
    iShowButton: Boolean = true,
    viewModel: MemberViewModel = hiltViewModel()
) {

    val memberData by viewModel.loginMemberData.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadLoginMemberData()
    })

    MemberCardContent(
        memberData = memberData,
        isShowButton = true,
        onClickEdit = {
            // TODO : 가입자 정보 수정 화면으로 이동
        }
    )
}

