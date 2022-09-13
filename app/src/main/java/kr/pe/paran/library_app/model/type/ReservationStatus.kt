package kr.pe.paran.library_app.model.type

enum class ReservationStatus(val status: Int) {
    None(1),
    Waiting(2),
    Completed(3),
    Canceled(4)
}