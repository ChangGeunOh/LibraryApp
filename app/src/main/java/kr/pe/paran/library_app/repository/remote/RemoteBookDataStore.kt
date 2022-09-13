package kr.pe.paran.library_app.repository.remote

import kr.pe.paran.library_app.model.AuthorData
import kr.pe.paran.library_app.model.BookData
import kr.pe.paran.library_app.network.NetworkClient
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.network.NetworkConst
import kr.pe.paran.library_app.repository.BookDataStore

class RemoteBookDataStore : BookDataStore {

    override suspend fun insertBookData(bookData: BookData, request: Int): NetworkStatus {
        return NetworkClient.post<BookData>(NetworkConst.MEMBER, bookData, request = request)
    }

    override suspend fun deleteBookData(bookData: BookData, request: Int): NetworkStatus {
        TODO("Not yet implemented")
    }

    override suspend fun updateBookData(bookData: BookData, request: Int): NetworkStatus {
        TODO("Not yet implemented")
    }

    override suspend fun loadBookData(bookData: BookData, request: Int): NetworkStatus {
        TODO("Not yet implemented")
    }

    override suspend fun searchBookList(query: String, request: Int): NetworkStatus {
        return NetworkClient.get<List<BookData>>(NetworkConst.BOOK, query, request = request)
    }

    override suspend fun searchAuthor(query: String, request: Int): NetworkStatus {
        return NetworkClient.get<List<AuthorData>>(NetworkConst.BOOK_AUTHOR, query)
    }

    override suspend fun insertAuthor(authorData: AuthorData, request: Int): NetworkStatus {
        return NetworkClient.post<AuthorData>(NetworkConst.BOOK_AUTHOR, authorData, request = request)
    }

}