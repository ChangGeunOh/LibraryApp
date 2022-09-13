package kr.pe.paran.library_app.navigation

sealed class Screen(val route: String) {

    object Splash: Screen("splash_screen")
    object Login: Screen("login_screen")

    object Scanner: Screen("scanner_screen")

    object BookInsert: Screen("book_insert_screen")
    object BookSearch: Screen("book_search_screen")

    object MemberHome: Screen("member_home_screen")
    object MemberCard: Screen("member_card_screen")
    object MemberInsert: Screen("member_insert_screen")
    object MemberManager: Screen("member_manager_screen")
    object MemberSearch: Screen("member_search_screen")

    object MemberReserved: Screen("member_reserved_screen")
    object MemberLoaned: Screen("member_loaned_screen")

    object LibrarianMenu: Screen("librarian_menu_screen")
    object LibrarianHome: Screen("librarian_home_screen")
    object LibrarianCard: Screen("librarian_card_screen")

    object BookItemInsert: Screen("book_item_insert_screen")
    object BookItemSearch: Screen("book_item_search_screen")

    object BookItemReservation: Screen("book_item_reservation_screen")
    object BookItemLoan: Screen("book_item_loan_screen")
    object BookItemReturn: Screen("book_item_return_screen")
    object BookItemManager: Screen("book_item_manager_screen")

}