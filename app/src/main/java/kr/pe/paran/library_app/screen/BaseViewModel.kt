package kr.pe.paran.library_app.screen

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.pe.paran.library_app.network.NetworkStatus

interface ViewModelInterface {

    /**
        의존성 주입을 위한 HiltViewModel 은 abstract class 를 상속하지 못함
        abstract class 대신 interface 를 상속하게 구현 함
     */

    var _networkStatus: MutableStateFlow<NetworkStatus>
        get() = MutableStateFlow<NetworkStatus>(NetworkStatus.IDLE)
        set(value)  {
            _networkStatus.value = value.value
        }

    val networkStatus: StateFlow<NetworkStatus>
        get() = _networkStatus.asStateFlow()

    fun setNetworkStatus(networkStatus: NetworkStatus) {
        _networkStatus.value = networkStatus
    }

    suspend fun clearNetworkState() {
        delay(500)
        _networkStatus.value = NetworkStatus.IDLE
    }

    var _message: MutableStateFlow<String>
        get() = MutableStateFlow("")
        set(value) {
            _message.value = value.value
        }
    val message: StateFlow<String>
        get() = _message.asStateFlow()

    suspend fun clearMessage() {
        delay(500)
        _message.value = ""
    }

    fun processNetworkStatus(networkStatus: NetworkStatus): Any? {
        _networkStatus.value = networkStatus
        if (networkStatus is NetworkStatus.SUCCESS) {
            return networkStatus.data
        }
        return null
    }

}