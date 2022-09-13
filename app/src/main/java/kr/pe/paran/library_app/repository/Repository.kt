package kr.pe.paran.library_app.repository

import kotlinx.coroutines.flow.Flow
import kr.pe.paran.library_app.model.*
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.repository.local.LocalCacheDataStore
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataStore: LocalCacheDataStore,
    private val memberDataStore: MemberDataStore,
    private val bookDataStore: BookDataStore,
    private val bookItemDataStore: BookItemDataStore

) {
     suspend fun insertMember(memberData: MemberData): NetworkStatus {
         return memberDataStore.insertMember(memberData = memberData)
     }

    suspend fun loginMember(accountData: AccountData): NetworkStatus {
        return memberDataStore.loadMember(accountData = accountData)
    }

    suspend fun loginLibrarian(accountData: AccountData): NetworkStatus {
        return memberDataStore.loadLibrarian(accountData = accountData)
    }


    suspend fun insertBookData(bookData: BookData, request: Int): NetworkStatus {
        return bookDataStore.insertBookData(bookData, request = request)
    }

    suspend fun searchAuthor(query: String, request: Int): NetworkStatus {
        return bookDataStore.searchAuthor(query = query, request = request)
    }

    suspend fun insertAuthor(authorData: AuthorData, request: Int): NetworkStatus {
        return bookDataStore.insertAuthor(authorData = authorData, request = request)
    }

    suspend fun searchBook(query: String, request: Int): NetworkStatus {
        return bookDataStore.searchBookList(query = query, request = request)
    }

    suspend fun searchBookItem(query: String, request: Int): NetworkStatus {
        return bookItemDataStore.searchBookItemList(query = query, request = request)
    }


    suspend fun insertBookItem(bookItemData: BookItemData, request: Int): NetworkStatus {
        return bookItemDataStore.insertBookItemData(bookItemData = bookItemData, request = request)
    }

    suspend fun updateBookItemStatus(reserveData: BookItemStatusData, request: Int): NetworkStatus {
        return bookItemDataStore.updateBookItemStatus(reserveData = reserveData, request = request)
    }

    suspend fun getBookItem(barcode: String, request: Int): NetworkStatus {
        return bookItemDataStore.getBookItem(barcode = barcode, request = request)
    }

    suspend fun updateBookItemData(bookItemData: BookItemData, request: Int): NetworkStatus {
        return bookItemDataStore.updateBookItemData(bookItemData = bookItemData, request = request)
    }


    suspend fun getMemberData(cardNo: String, request: Int): NetworkStatus {
        return memberDataStore.getMemberData(cardNo = cardNo, request = request )
    }

    suspend fun searchMemberData(searchData: SearchData): NetworkStatus {
        return memberDataStore.searchMemberData(searchData = searchData)
    }

    suspend fun searchMemberList(searchData: SearchData): NetworkStatus {
        return memberDataStore.searchMemberList(searchData = searchData)
    }

    suspend fun updateMemberData(memberData: MemberData): NetworkStatus {
        return memberDataStore.updateMember(memberData = memberData)
    }

    suspend fun getBookItemList(searchData: SearchData, request: Int): NetworkStatus {
        return bookItemDataStore.getBookItemList(searchData, request = request)
    }

    suspend fun getLoanedBookItemList(searchData: SearchData, request: Int): NetworkStatus {
        return bookItemDataStore.getLoanedBookItemList(searchData = searchData, request = request)
    }

    suspend fun setMemberData(memberData: MemberData) {
        localDataStore.saveMemberData(memberData)
    }

    suspend fun setLibrarianData(librarianData: LibrarianData) {
        localDataStore.saveLibrarianData(librarianData = librarianData)
    }

    suspend fun loadLoginMemberData(): Flow<MemberData> {
        return localDataStore.loadMemberData()
    }


}