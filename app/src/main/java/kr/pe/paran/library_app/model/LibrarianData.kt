package kr.pe.paran.library_app.model

@kotlinx.serialization.Serializable
data class LibrarianData (
    val id: Int,
    val personData: PersonData,
    val accountData: AccountData,
    val cardNumber: String = "",
    val barCode: String = "",
    var uuid: String = ""
)