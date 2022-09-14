package kr.pe.paran.library_app.repository

import kotlinx.coroutines.flow.Flow
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData

interface CacheDataStore {

    suspend fun loadMemberData(): Flow<MemberData>
    suspend fun saveMemberData(memberData: MemberData)
    suspend fun loadLibrarianData(): Flow<LibrarianData>
    suspend fun saveLibrarianData(librarianData: LibrarianData)
    suspend fun saveToken(token: String)
    suspend fun loadToken(): Flow<String>
}