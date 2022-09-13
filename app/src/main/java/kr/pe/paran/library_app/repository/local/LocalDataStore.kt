package kr.pe.paran.library_app.repository.local

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object LocalDataStore {

    var userid by mutableStateOf("11111111")
    var memberCardCode by mutableStateOf("1234567890123456")
}