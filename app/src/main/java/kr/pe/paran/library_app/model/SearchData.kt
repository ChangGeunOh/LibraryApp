package kr.pe.paran.library_app.model

import kr.pe.paran.library_app.model.type.SearchType

@kotlinx.serialization.Serializable
data class SearchData(
    val query: String,
    val type : SearchType
)
