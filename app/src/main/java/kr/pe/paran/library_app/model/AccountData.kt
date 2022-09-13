package kr.pe.paran.library_app.model

import kr.pe.paran.library_app.model.type.AccountStatus
import kr.pe.paran.library_app.model.type.AccountType

@kotlinx.serialization.Serializable
data class AccountData(
    val userid: String = "",
    val userpw: String = "",
    val accountType: AccountType = AccountType.MEMBER,
    var status: AccountStatus = AccountStatus.Active
) {
    fun isNotEmpty(): Boolean {
        return userid.isNotEmpty() && userpw.isNotEmpty()
    }
}
