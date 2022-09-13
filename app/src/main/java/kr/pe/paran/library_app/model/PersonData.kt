package kr.pe.paran.library_app.model

@kotlinx.serialization.Serializable
data class PersonData(
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var addressData: AddressData = AddressData()
) {
    fun isNotEmpty(): Boolean {
        return name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && addressData.address.isNotEmpty()
    }

}
