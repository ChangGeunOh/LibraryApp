package kr.pe.paran.library_app.model.type

enum class BookStatus(val status: Int) {
    Available(1),
    Reserved(2),
    Loaned(3),
    Lost(4)
}