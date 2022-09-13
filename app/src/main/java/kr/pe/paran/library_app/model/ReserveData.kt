package kr.pe.paran.library_app.model

import kr.pe.paran.library_app.model.type.ReservationStatus
import java.text.SimpleDateFormat
import java.util.*

@kotlinx.serialization.Serializable
data class ReserveData(
    val reservationBookItemCode: Int = 0,
    val reservationDate: Long = 0L,
    val reservationUserId: String = "",
    val reservationCardNo: String = "",
    val reservationStatus: ReservationStatus = ReservationStatus.None
) {
    fun getReserveDate(): String {
        return if (reservationDate == 0L) "-" else SimpleDateFormat(
            "yyyy.MM.dd",
            Locale.getDefault()
        ).format(reservationDate)
    }
}
