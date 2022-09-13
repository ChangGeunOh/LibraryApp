package kr.pe.paran.library_app.model

import kr.pe.paran.library_app.model.type.BookStatus

@kotlinx.serialization.Serializable
data class BookItemStatusData(
    val bookItemBarCode: String,
    val memberCardCode: String = "",
    val bookItemStatus: BookStatus
)