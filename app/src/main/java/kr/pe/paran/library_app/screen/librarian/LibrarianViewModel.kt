package kr.pe.paran.library_app.screen.librarian

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.PersonData
import kr.pe.paran.library_app.repository.Repository
import javax.inject.Inject

@HiltViewModel
class LibrarianViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val librarianData = LibrarianData(
        id = 1,
        personData = PersonData(name = "진지현"),
        cardNumber = "1234567890",
        barCode = "1234-4567-8901-1234",
        accountData = AccountData()
    )
}