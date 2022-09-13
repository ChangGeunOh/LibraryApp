package kr.pe.paran.library_app.screen.member

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.model.*
import kr.pe.paran.library_app.model.type.SearchType
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_MEMBER_LOANED_BOOK_LIST
import kr.pe.paran.library_app.network.NetworkConst.REQUEST_MEMBER_RESERVED_BOOK_LIST
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
    private var _memberData = MutableStateFlow<MemberData>(
        MemberData(
            id = 1,
            personData = PersonData(name = "진지현"),
            cardNumber = "1234567890",
            barCode = "1234-4567-8901-1234",
            accountData = AccountData()
        )
    )
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
            val data = processNetworkStatus(networkStatus = networkStatus)
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

    /** MemberReservedScreen.kt ---------------------------> */
    private var _reservedBookItemList = MutableStateFlow<MutableList<BookItemData>>(mutableListOf())
    val reservedBookItemList = _reservedBookItemList.asStateFlow()

    fun getBookItemList(memberCardNo: String) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            val searchData = SearchData(
                query = memberCardNo,
                type = SearchType.CARDNO
            )
            val networkStatus = repository.getBookItemList(searchData = searchData, request = REQUEST_MEMBER_RESERVED_BOOK_LIST)
            processNetworkStatus(networkStatus = networkStatus)?.let {
                @Suppress("UNCHECKED_CAST")
                _reservedBookItemList.value = it as MutableList<BookItemData>
            }
        }
    }

    /** <----------------------------MemberReservedScreen.kt */

    /** MemberLoanedScreen.kt -----------------------------> */
    private var _loanedBookItemList = MutableStateFlow<MutableList<BookItemData>>(mutableListOf())
    val loanedBookItemList = _loanedBookItemList.asStateFlow()

    fun getLoanedBookItemList(memberCardNo: String) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            val searchData = SearchData(
                query = memberCardNo,
                type = SearchType.CARDNO
            )
            val networkStatus = repository.getLoanedBookItemList(searchData = searchData, request = REQUEST_MEMBER_LOANED_BOOK_LIST)
            processNetworkStatus(networkStatus = networkStatus)?.let {
                @Suppress("UNCHECKED_CAST")
                _reservedBookItemList.value = it as MutableList<BookItemData>
            }
        }
    }
    /** <----------------------------- MemberLoanedScreen.kt */

    private var _loginMemberData = MutableStateFlow<MemberData>(MemberData())
    var loginMemberData = _loginMemberData.asStateFlow()

    fun loadLoginMemberData() {
        viewModelScope.launch {
            repository.loadLoginMemberData().collectLatest {
                _loginMemberData.value = it
            }
        }
    }

}