package kr.pe.paran.library_app.model

import kr.pe.paran.library_app.model.type.BookStatus
import java.text.SimpleDateFormat
import java.util.*

@kotlinx.serialization.Serializable
data class BookItemData(
    var id: Int = -1,
    var bookData: BookData = BookData(),
    var libraryName: String = "",
    var barCode: String = Date().time.toString().substring(startIndex = 1),
    var purchaseDate: Long = Date().time,
    var rackLocationIdentifier: String = "",
    var status: BookStatus = BookStatus.Available,
    val borrowDate: Long = 0L,
    val dueDate: Long = 0L
) {
    fun getPurchaseDate(): String {
        return SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(purchaseDate)
    }

    fun setPurchaseDate(date: String) {
        val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        purchaseDate = formatter.parse(date)?.time ?: Date().time
    }

    fun getBorrowDate(): String {
        return SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(purchaseDate)
    }

    fun setBorrowDate(date: String) {
        val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        purchaseDate = formatter.parse(date)?.time ?: Date().time
    }

    fun getDueDate(): String {
        return if (dueDate == 0L) "-" else SimpleDateFormat(
            "yyyy.MM.dd",
            Locale.getDefault()
        ).format(purchaseDate)
    }

    fun setDueDate(date: String) {
        val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        purchaseDate = formatter.parse(date)?.time ?: 0
    }

    fun isNotEmpty(): Boolean {
        return bookData.isNotEmpty() && libraryName.isNotEmpty() && rackLocationIdentifier.isNotEmpty()
    }

    fun bookItemStatusList(): List<String> {
        return listOf(
            BookStatus.Available.name,
            BookStatus.Reserved.name,
            BookStatus.Loaned.name,
            BookStatus.Lost.name
        )
    }

    fun isAvailableReservation(): Boolean {
        return (status == BookStatus.Available) || (status == BookStatus.Loaned)
    }

    fun getStatusKor(): String {
        return when (status) {
            BookStatus.Available -> "보관중"
            BookStatus.Reserved -> "예약중"
            BookStatus.Loaned -> "대여중"
            BookStatus.Lost -> "분실"
        }
    }
}
