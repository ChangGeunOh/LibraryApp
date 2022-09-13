package kr.pe.paran.library_app.model

import kr.pe.paran.model.type.BookFormat
import java.text.SimpleDateFormat
import java.util.*

@kotlinx.serialization.Serializable
data class BookData(
    var id: Int = -1,
    var isbn: String  = "",
    var title: String= "",
    var subject: String= "",
    var bookFormat: BookFormat = BookFormat.HardCover,
    var authorData: AuthorData = AuthorData(),
    var language: String = "한국어",
    var publicationDate: Long = Date().time,
    var price: Int = 0,
) {

    fun getDate(): String {
        return SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(publicationDate)
    }

    fun setDate(date: String) {
        val formatter  = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        publicationDate = formatter.parse(date)?.time ?: Date().time
    }

    fun isNotEmpty(): Boolean {
        return isbn.isNotEmpty() && title.isNotEmpty() && subject.isNotEmpty() && language.isNotEmpty() && authorData.name.isNotEmpty()
    }
}