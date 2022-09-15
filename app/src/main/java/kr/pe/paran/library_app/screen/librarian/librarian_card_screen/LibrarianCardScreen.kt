package kr.pe.paran.library_app.screen.librarian.librarian_card_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.screen.librarian.LibrarianViewModel
import kr.pe.paran.library_app.screen.member.MemberViewModel

@Composable
fun LibrarianCardScreen(
    navController: NavController,
    viewModel: LibrarianViewModel = hiltViewModel()
) {
    val librarianData by viewModel.librarianData.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadLibrarianData()
    })

    LibrarianCardContent(librarianData = librarianData)
}