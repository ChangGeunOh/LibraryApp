package kr.pe.paran.library_app.model.type

@kotlinx.serialization.Serializable
enum class AccountStatus(val status: Int) {
    Active(1),
    DeActive(2)
}