package kr.pe.paran.library_app.screen.login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.model.AccountData
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
            _networkStatus.value = repository.login(accountData = accountData)
            Log.i(":::::", "NetworkStatus:::::${_networkStatus.value.toString()}")
        }
    }

    fun loginLibrarian(accountData: AccountData) {
        _networkStatus.value = NetworkStatus.LOADING
        viewModelScope.launch {
            _networkStatus.value = repository.loginLibrarian(accountData = accountData)
        }

    }
}
