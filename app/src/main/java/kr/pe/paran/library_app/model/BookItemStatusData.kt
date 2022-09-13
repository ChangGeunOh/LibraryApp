package kr.pe.paran.library_app.model

import kr.pe.paran.library_app.model.type.BookStatus
import kr.pe.paran.library_app.model.type.ReservationStatus

@kotlinx.serialization.Serializable
data class BookItemStatusData(
    val bookItemBarCode: String,
    val memberCardCode: String = "",
    val bookItemStatus: BookStatus,
    val reservationStatus: ReservationStatus = ReservationStatus.None
)