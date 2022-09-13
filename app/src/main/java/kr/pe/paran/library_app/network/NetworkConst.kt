package kr.pe.paran.library_app.network

object NetworkConst {


    private const val BASE_URL = "http://172.30.1.61:8089"

    const val MEMBER = "$BASE_URL/member"
    const val MEMBER_ACCOUNT = "$BASE_URL/member/account"
    const val MEMBER_SEARCH = "$BASE_URL/member/search"

    const val BOOK = "$BASE_URL/book"
    const val BOOK_AUTHOR = "$BASE_URL/book/author"

    const val BOOK_ITEM = "$BASE_URL/book_item"
    const val BOOK_ITEM_RESERVE = "$BASE_URL/book_item/status"
    const val BOOK_ITEM_BARCODE = "$BASE_URL/book_item/barcode"

    const val REQUEST_SEARCH_AUTHOR = 1000
    const val REQUEST_INSERT_AUTHOR = 1100

    const val REQUEST_INSERT_BOOK = 2000
    const val REQUEST_SEARCH_BOOK = 2100

    const val REQUEST_INSERT_BOOK_ITEM = 3000
    const val REQUEST_SEARCH_BOOK_ITEM = 3100
    const val REQUEST_STATUS_BOOK_ITEM = 3200
    const val REQUEST_LOAN_BOOK_ITEM = 3300
    const val REQUEST_CODE_BOOK_ITEM = 3400
    const val REQUEST_UPDATE_BOOK_ITEM = 3500

    const val REQUEST_CODE_MEMBER = 4000

}