package kr.pe.paran.library_app.navigation

import MemberManagerScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kr.pe.paran.library_app.screen.book.book_insert_screen.BookInsertScreen
import kr.pe.paran.library_app.screen.book.book_search_screen.BookSearchScreen
import kr.pe.paran.library_app.screen.book_item.book_item_insert_screen.BookItemInsertScreen
import kr.pe.paran.library_app.screen.book_item.book_item_loan_screen.BookItemLoanScreen
import kr.pe.paran.library_app.screen.book_item.book_item_manager_screen.BookItemManagerScreen
import kr.pe.paran.library_app.screen.book_item.book_item_reservation_screen.BookItemReservationScreen
import kr.pe.paran.library_app.screen.book_item.book_item_return_screen.BookItemReturnScreen
import kr.pe.paran.library_app.screen.book_item.book_item_search_screen.BookItemSearchScreen
import kr.pe.paran.library_app.screen.librarian.librarian_card_screen.LibrarianCardScreen
import kr.pe.paran.library_app.screen.librarian.librarian_home_screen.LibrarianHomeScreen
import kr.pe.paran.library_app.screen.member.member_card_screen.MemberCardScreen
import kr.pe.paran.library_app.screen.login_screen.LoginScreen
import kr.pe.paran.library_app.screen.member.member_home_screen.MemberHomeScreen
import kr.pe.paran.library_app.screen.member.member_insert_screen.MemberInsertScreen
import kr.pe.paran.library_app.screen.member.member_search_screen.MemberSearchScreen
import kr.pe.paran.library_app.screen.scanner_screen.ScannerScreen
import kr.pe.paran.library_app.screen.splash_screen.SplashScreen

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navHostController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navHostController)
        }

        composable(route = Screen.Scanner.route) {
            ScannerScreen(navController = navHostController)
        }

        composable(route = Screen.MemberHome.route) {
            MemberHomeScreen(navController = navHostController)
        }

        composable(route = Screen.MemberCard.route) {
            MemberCardScreen(navController = navHostController)
        }

        composable(route = Screen.MemberInsert.route) {
            MemberInsertScreen(navController = navHostController)
        }

        composable(route = Screen.MemberManager.route) {
            MemberManagerScreen(navController = navHostController)
        }

        composable(route = Screen.MemberSearch.route) {
            MemberSearchScreen(navController = navHostController)
        }

        composable(route = Screen.LibrarianHome.route) {
            LibrarianHomeScreen(navController = navHostController)
        }

        composable(route = Screen.LibrarianMenu.route) {
            LibrarianHomeScreen(navController = navHostController)
        }

        composable(route = Screen.LibrarianCard.route) {
            LibrarianCardScreen(navController = navHostController)
        }

        composable(route = Screen.BookInsert.route) {
            BookInsertScreen(navController = navHostController)
        }

        composable(route = Screen.BookSearch.route) {
            BookSearchScreen(navController = navHostController)
        }

        composable(route = Screen.BookItemSearch.route) {
            BookItemSearchScreen(navController = navHostController)
        }

        composable(route = Screen.BookItemInsert.route) {
            BookItemInsertScreen(navController = navHostController)
        }

        composable(route = Screen.BookItemReservation.route) {
            BookItemReservationScreen(navController = navHostController)
        }

        composable(route = Screen.BookItemLoan.route) {
            BookItemLoanScreen(navController = navHostController)
        }

        composable(route = Screen.BookItemReturn.route) {
            BookItemReturnScreen(navController = navHostController)
        }

        composable(route = Screen.BookItemManager.route) {
            BookItemManagerScreen(navController = navHostController)
        }
    }
}