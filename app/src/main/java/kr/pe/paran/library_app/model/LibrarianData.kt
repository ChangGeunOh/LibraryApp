package kr.pe.paran.library_app.model

@kotlinx.serialization.Serializable
data class LibrarianData (
    val id: Int = -1,
    val personData: PersonData = PersonData(),
    val accountData: AccountData = AccountData(),
    val cardNumber: String = "",
    val barCode: String = "",
    var uuid: String = ""
)