package kr.pe.paran.library_app.repository

import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.model.BookItemStatusData
import kr.pe.paran.library_app.network.NetworkStatus

interface BookItemDataStore {
    suspend fun insertBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus
    suspend fun deleteBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus
    suspend fun updateBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus
    suspend fun loadBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus
    suspend fun searchBookItemList(query: String, request: Int): NetworkStatus
    suspend fun updateBookItemStatus(reserveData: BookItemStatusData, request: Int): NetworkStatus
    suspend fun getBookItem(barcode: String, request: Int): NetworkStatus
}