package kr.pe.paran.library_app.repository

import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.SearchData
import kr.pe.paran.library_app.network.NetworkStatus

interface MemberDataStore {
    suspend fun insertMember(memberData: MemberData): NetworkStatus
    suspend fun loadMember(memberData: MemberData): NetworkStatus
    suspend fun updateMember(memberData: MemberData): NetworkStatus
    suspend fun deleteMember(memberData: MemberData): NetworkStatus
    suspend fun loadMember(accountData: AccountData): NetworkStatus

    suspend fun loadLibrarian(accountData: AccountData): NetworkStatus

    suspend fun getMemberData(cardNo: String, request: Int): NetworkStatus
    suspend fun searchMemberData(searchData: SearchData): NetworkStatus
    suspend fun searchMemberList(searchData: SearchData): NetworkStatus
}