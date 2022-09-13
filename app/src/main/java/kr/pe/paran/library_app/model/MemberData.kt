package kr.pe.paran.library_app.model

import kr.pe.paran.library_app.network.NetworkStatus

@kotlinx.serialization.Serializable
data class MemberData(
    val id: Int = -1,
    val personData: PersonData = PersonData(),
    val accountData: AccountData = AccountData(),
    val cardNumber: String = "",
    val barCode: String = "",
    var bookItems: MutableList<BookItemData> = mutableListOf(),
    var uuid: String = ""
) {
    fun isValid(): Boolean {
        return personData.isNotEmpty() && accountData.isNotEmpty()
    }

}