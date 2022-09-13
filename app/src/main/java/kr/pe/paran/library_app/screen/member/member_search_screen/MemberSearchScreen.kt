package kr.pe.paran.library_app.screen.member.member_search_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.GenieSearchView
import kr.pe.paran.library_app.component.SearchAppBar
import kr.pe.paran.library_app.model.type.SearchType
import kr.pe.paran.library_app.screen.member.MemberViewModel

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MemberSearchScreen(navController: NavController, viewModel: MemberViewModel = hiltViewModel()) {

    val memberList by viewModel.memberList.collectAsState()

    Scaffold(topBar = {
        GenieSearchView(
            holderText = "Search here...",
            onTextChange = {
                viewModel.searchMemberData(query = it, searchType = SearchType.NAME)
            },
            onSearchClicked = { },
            onCloseClicked = { navController.popBackStack() },
            query = ""
        )
    }) {
        MemberSearchContent(memberList = memberList, onClickItem = {
            navController.previousBackStackEntry?.savedStateHandle?.set(MEMBER_DATA, it)
        })
    }
}

const val MEMBER_DATA = "member_data"