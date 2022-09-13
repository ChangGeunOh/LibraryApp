package kr.pe.paran.library_app.screen.librarian.librarian_menu_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kr.pe.paran.library_app.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LibrarianMenuScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        LibrarianMenuContent(onClickMenu = {
            navigation(navController, it)
        })
    }
}

private fun navigation(navController: NavController, index: Int) {
    when(index) {
        1 -> navController.navigate(Screen.BookInsert.route)
        2 -> navController.navigate(Screen.BookItemInsert.route)
        3 -> navController.navigate(Screen.BookItemLoan.route)
        4 -> navController.navigate(Screen.BookItemReturn.route)
        5 -> navController.navigate(Screen.BookItemManager.route)
        6 -> navController.navigate(Screen.MemberManager.route)
    }
}