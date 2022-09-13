package kr.pe.paran.library_app.repository

import kr.pe.paran.library_app.model.AuthorData
import kr.pe.paran.library_app.model.BookData
import kr.pe.paran.library_app.network.NetworkStatus

interface BookDataStore {
    suspend fun insertBookData(bookData: BookData, request: Int): NetworkStatus
    suspend fun deleteBookData(bookData: BookData, request: Int): NetworkStatus
    suspend fun updateBookData(bookData: BookData, request: Int): NetworkStatus
    suspend fun loadBookData(bookData: BookData, request: Int): NetworkStatus
    suspend fun searchBookList(query: String, request: Int): NetworkStatus
    suspend fun searchAuthor(query: String, request: Int): NetworkStatus
    suspend fun insertAuthor(authorData: AuthorData, request: Int): NetworkStatus
}