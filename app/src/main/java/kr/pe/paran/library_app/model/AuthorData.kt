package kr.pe.paran.library_app.model

@kotlinx.serialization.Serializable
data class AuthorData(
    val id: Int = -1,
    var name: String = "",
    var description: String = ""
)