package kr.pe.paran.library_app.screen.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.SearchData
import kr.pe.paran.library_app.model.type.SearchType
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.repository.Repository
import kr.pe.paran.library_app.screen.ViewModelInterface
import kr.pe.paran.library_app.utils.Logcat
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    val repository: Repository
) : ViewModel(), ViewModelInterface {

    fun saveMemberData(memberData: MemberData) {
        _networkStatus.value = NetworkStatus.LOADING

        Logcat.i(memberData.toString())
        viewModelScope.launch {
            _networkStatus.value = repository.insertMember(memberData)
        }
    }


    // MemberData
    private var _memberData = MutableStateFlow<MemberData?>(null)
    val memberData = _memberData.asStateFlow()

    fun searchMemberData(query: String, searchType: SearchType) {
        val searchData = SearchData(query = query, searchType)
        _networkStatus.value = NetworkStatus.LOADING

        viewModelScope.launch {
            val networkStatus = repository.searchMemberData(searchData)
            val data = processNetworkStatus(networkStatus = networkStatus)
            data?.let {
                _memberData.value = it as MemberData
            } ?: kotlin.run {
                _message.value = "검색된 정보가 없습니다."
            }
            clearMessage()
        }
    }

    fun setMemberData(memberData: MemberData) {
        _memberData.value = memberData
    }


    /** BookItemScreen.kt ---------------------------------> */

    // 이름 검색 결과 List
    private var _memberList = MutableStateFlow<MutableList<MemberData>>(mutableListOf())
    val memberList = _memberList.asStateFlow()

    // 이름으로 MemberData 검색
    fun searchMemberList(query: String, searchType: SearchType) {
        val searchData = SearchData(query = query, searchType)
        _networkStatus.value = NetworkStatus.LOADING

        viewModelScope.launch {
            val networkStatus = repository.searchMemberList(searchData)
            val data = processNetworkStatus(networkStatus =  networkStatus)
            data?.let {
                @Suppress("UNCHECKED_CAST")
                _memberList.value = it as MutableList<MemberData>
            } ?: kotlin.run {
                _memberList.value = mutableListOf()
            }
        }
    }

    fun updateMemberData(memberData: MemberData) {
        _networkStatus.value = NetworkStatus.LOADING

        Logcat.i(memberData.toString())
        viewModelScope.launch {
            val networkStatus = repository.updateMemberData(memberData)
            val data = processNetworkStatus(networkStatus = networkStatus)
            data?.let {
                _memberData.value = it as MemberData
            }

        }
    }

    /** <--------------------------------- BookItemScreen.kt */

}