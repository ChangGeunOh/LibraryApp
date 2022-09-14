package kr.pe.paran.library_app.repository.remote

import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.SearchData
import kr.pe.paran.library_app.network.NetworkClient
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.network.NetworkConst
import kr.pe.paran.library_app.repository.MemberDataStore

class RemoteMemberDataStore : MemberDataStore {

    private var _memberData: MemberData = MemberData()

    override suspend fun insertMember(memberData: MemberData): NetworkStatus {
        return NetworkClient.post<MemberData>(NetworkConst.MEMBER, memberData)
    }

    override suspend fun loadMember(memberData: MemberData): NetworkStatus {
        return NetworkClient.get<MemberData>("", memberData)
    }

    override suspend fun loadMember(accountData: AccountData): NetworkStatus {
        return NetworkClient.post<MemberData>(NetworkConst.MEMBER_ACCOUNT, accountData)
    }

    override suspend fun updateMember(memberData: MemberData): NetworkStatus {
        return NetworkClient.put<MemberData>(NetworkConst.MEMBER, memberData)
    }

    override suspend fun deleteMember(memberData: MemberData): NetworkStatus {
        TODO("Not yet implemented")
    }

    override suspend fun loadLibrarian(accountData: AccountData): NetworkStatus {
        return NetworkClient.post<LibrarianData>(NetworkConst.MEMBER_ACCOUNT, accountData)
    }

    override suspend fun getMemberData(cardNo: String, request: Int): NetworkStatus {
        return NetworkClient.get<MemberData>(NetworkConst.MEMBER, cardNo, request = request)
    }

    override suspend fun searchMemberData(searchData: SearchData): NetworkStatus {
        return NetworkClient.post<MemberData>(NetworkConst.MEMBER_SEARCH, searchData)
    }

    override suspend fun searchMemberList(searchData: SearchData): NetworkStatus {
        return NetworkClient.post<List<MemberData>>(NetworkConst.MEMBER_SEARCH, searchData)
    }

    override suspend fun putMemberData(data: Any): NetworkStatus {
        return NetworkClient.put<Any>(NetworkConst.MEMBER, data)
    }

}

