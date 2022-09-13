package kr.pe.paran.library_app.screen.member.member_insert_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.DaumAddressDialog
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.model.*
import kr.pe.paran.library_app.model.type.AccountStatus
import kr.pe.paran.library_app.network.NetworkStatus
import kr.pe.paran.library_app.screen.member.MemberViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MemberInsertScreen(navController: NavController, viewModel: MemberViewModel = hiltViewModel()) {

    var isShowAddressDialog by remember { mutableStateOf(false) }
    var memberData by remember { mutableStateOf(MemberData()) }
    val networkStatus by viewModel.networkStatus.collectAsState()

    Scaffold(topBar = {
        NavigationAppBar(
            title = "회원 등록",
            onClickBack = { navController.popBackStack() })
    }) {
        MemberInsertContent(
            onClickSearch = {
                isShowAddressDialog = true
            },
            memberData = memberData,
            onChangValue = { memberData = it},
            onClickSave = { memberData ->
                viewModel.saveMemberData(memberData)
            }
        )
    }

    if (isShowAddressDialog) {
        DaumAddressDialog(onFindAddress = { zipCode, roadAddress, _ ->
            val addressData = AddressData(
                zipCode = zipCode,
                address = roadAddress
            )
            memberData = memberData.copy(personData = memberData.personData.copy(addressData = addressData))
            isShowAddressDialog = false
        },
            onDismissDialog = { isShowAddressDialog = false }
        )
    }

    if (networkStatus is NetworkStatus.SUCCESS) {
        val memberData = (networkStatus as NetworkStatus.SUCCESS).data as MemberData
        viewModel.saveMemberData(memberData)
        ShowToast(message = "정상적으로 회원 등록이 되었습니다.")
        navController.popBackStack()
    } else if (networkStatus is NetworkStatus.FAILURE) {
        val message = (networkStatus as NetworkStatus.FAILURE).message
        if (message.contains("409")) {
            ShowToast(message = "사용자ID가 등록되어 있습니다.")
        }
    }
}

@Composable
fun ShowToast(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}

private val memberData = MemberData(
    id = -1,
    personData = PersonData(
        name = "오창근",
        email = "fiveroot@gmail.com",
        phone = "01066552842",
        addressData = AddressData(
            id = -1,
            zipCode = "13606",
            address = "경기 성남시 분당구 불정로 90  (정자동)",
            extraAddress = "13층"
        )
    ),
    accountData = AccountData(
        userid = "12345678",
        userpw = "12345678",
        status = AccountStatus.Active
    ),
    cardNumber = "",
    barCode = "",
    bookItems = mutableListOf(),
    uuid = ""
)
