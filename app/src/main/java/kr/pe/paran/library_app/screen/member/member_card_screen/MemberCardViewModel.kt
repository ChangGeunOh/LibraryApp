package kr.pe.paran.library_app.screen.member.member_card_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.PersonData
import kr.pe.paran.library_app.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MemberCardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val memberData = MemberData(
        id = 1,
        personData = PersonData(name = "진지현"),
        cardNumber = "123456789012",
        barCode = "1234-4567-8901-1234",
        accountData = AccountData()
    )
}


