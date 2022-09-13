package kr.pe.paran.library_app.model

@kotlinx.serialization.Serializable
data class AddressData(
    val id: Int = -1,
    var zipCode: String = "",
    val address: String = "",
    var extraAddress: String = "",
) {
    fun getSummary(): String {
        return "$zipCode $address"
    }
}

