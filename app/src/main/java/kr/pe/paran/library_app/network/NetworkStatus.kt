package kr.pe.paran.library_app.network

sealed class NetworkStatus {
    object IDLE: NetworkStatus()
    object LOADING: NetworkStatus()
    data class SUCCESS(val data: Any, var request: Int = 0): NetworkStatus()
    data class FAILURE(val message: String, var request: Int = 0): NetworkStatus()
}
