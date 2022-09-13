package kr.pe.paran.library_app.repository.remote

import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.model.BookItemStatusData
import kr.pe.paran.library_app.model.SearchData
import kr.pe.paran.library_app.network.NetworkClient
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.network.NetworkConst
import kr.pe.paran.library_app.repository.BookItemDataStore

class RemoteBookItemDataStore : BookItemDataStore {
    override suspend fun insertBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus {
        return NetworkClient.post<BookItemData>(NetworkConst.BOOK_ITEM, bookItemData, request)
    }

    override suspend fun deleteBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus {
        TODO("Not yet implemented")
    }

    override suspend fun updateBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus {
        return NetworkClient.put<BookItemData>(NetworkConst.BOOK_ITEM, bookItemData)
    }

    override suspend fun loadBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus {
        TODO("Not yet implemented")
    }

    override suspend fun searchBookItemList(query: String, request: Int): NetworkStatus {
        return NetworkClient.get<List<BookItemData>>(NetworkConst.BOOK_ITEM, query, request = request)
    }

    override suspend fun updateBookItemStatus(reserveData: BookItemStatusData, request: Int): NetworkStatus {
        return NetworkClient.post<BookItemData>(NetworkConst.BOOK_ITEM_RESERVE, data = reserveData, request = request )
    }

    override suspend fun getBookItem(barcode: String, request: Int): NetworkStatus {
        return NetworkClient.get<BookItemData>(NetworkConst.BOOK_ITEM_BARCODE, data = barcode, request =  request)
    }

    override suspend fun getBookItemList(searchData: SearchData, request: Int): NetworkStatus {
        return NetworkClient.post<List<BookItemData>>(NetworkConst.BOOK_ITEM_SEARCH, data = searchData, request = request )
    }

    override suspend fun getLoanedBookItemList(
        searchData: SearchData,
        request: Int
    ): NetworkStatus {
        return NetworkClient.post<List<BookItemData>>(NetworkConst.BOOK_ITEM_LOANED, data = searchData, request = request)
    }

}