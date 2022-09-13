package kr.pe.paran.library_app.screen.librarian.librarian_home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.screen.librarian.LibrarianViewModel

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LibrarianHomeScreen(
    navController: NavController,
    viewModel: LibrarianViewModel = hiltViewModel()
) {
    val librarianData = viewModel.librarianData

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        LibrarianHomeContent(
            onClickMenu = {
                navigation(navController = navController, index = it)
            },
            librarianData = librarianData
        )
    }
}

private fun navigation(navController: NavController, index: Int) {
    when (index) {
        1 -> navController.navigate(Screen.BookInsert.route)
        2 -> navController.navigate(Screen.BookItemInsert.route)
        3 -> navController.navigate(Screen.BookItemLoan.route)
        4 -> navController.navigate(Screen.BookItemReturn.route)
        5 -> navController.navigate(Screen.BookItemManager.route)
        6 -> navController.navigate(Screen.MemberManager.route)
    }
}