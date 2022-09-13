package kr.pe.paran.library_app.utils

import kr.pe.paran.library_app.model.AuthorData
import kr.pe.paran.library_app.model.BookData
import kr.pe.paran.library_app.model.BookItemData
import kr.pe.paran.library_app.model.type.BookStatus
import kr.pe.paran.model.type.BookFormat

object Constants {

    val authorData = AuthorData(
        id = 3,
        name = "송지효",
        description = "송지효(1981년 8월 15일 ~ )는 대한민국의 배우이자 방송인이다."
    )
    val bookData = BookData(
        id = 5,
        isbn = "9791162245026",
        title = "이것이 안드로이드다 with 코틀린",
        subject = "컴퓨터 / 모바일 / 안드로이드",
        bookFormat = BookFormat.Paperback,
        authorData = authorData,
        language = "한국어",
        publicationDate = 1662516384751,
        price = 34000
    )

    val bookItemData = BookItemData(
        id = 5,
        bookData = bookData,
        libraryName = "분당",
        barCode = "1662528363423",
        purchaseDate = 1662528363423,
        rackLocationIdentifier = "13층",
        status = BookStatus.Available,
        borrowDate = 0,
        dueDate = 0
    )

}