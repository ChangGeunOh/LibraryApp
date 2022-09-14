package kr.pe.paran.library_app.screen.login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.type.AccountType
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.repository.Repository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _networkStatus = MutableStateFlow<NetworkStatus>(NetworkStatus.IDLE)
    val networkStatus = _networkStatus.asStateFlow()

    fun login(accountData: AccountData) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            if (accountData.accountType == AccountType.MEMBER) {
                _networkStatus.value = repository.loginMember(accountData = accountData)
            } else {
                _networkStatus.value = repository.loginLibrarian(accountData = accountData)
            }
        }
    }

    fun setMemberData(memberData: MemberData) {
        viewModelScope.launch {
            repository.setMemberData(memberData)
        }
    }

    fun setLibrarianData(librarianData: LibrarianData) {
        viewModelScope.launch {
            repository.setLibrarianData(librarianData = librarianData)
        }
    }

    private var _token = MutableStateFlow("")
    val token = _token.asStateFlow()

    fun loadToken() {
        viewModelScope.launch {
            repository.loadToken().collectLatest {
                _token.value = it
            }
        }
    }

    fun putMemberData(data: Any) {
        viewModelScope.launch {
            repository.putMemberData(data = data)
        }
    }

}
