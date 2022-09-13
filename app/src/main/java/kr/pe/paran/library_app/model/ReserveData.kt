package kr.pe.paran.library_app.model

import java.text.SimpleDateFormat
import java.util.*

@kotlinx.serialization.Serializable
data class ReserveData(
    val bookItemCode: Int = 0,
    val reserveDate: Long = 0L,
    val reserveUserId: String = "",
) {
    fun getReserveDate(): String {
        return if (reserveDate == 0L) "-" else SimpleDateFormat(
            "yyyy.MM.dd",
            Locale.getDefault()
        ).format(reserveDate)
    }
}
