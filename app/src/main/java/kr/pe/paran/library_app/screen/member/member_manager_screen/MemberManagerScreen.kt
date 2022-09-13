import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.component.DaumAddressDialog
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.model.AddressData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.screen.member.MemberViewModel
import kr.pe.paran.library_app.screen.member.member_search_screen.MEMBER_DATA

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MemberManagerScreen(
    navController: NavController,
    viewModel: MemberViewModel = hiltViewModel()
) {

    // 회원 검색 결과
    val memberData by viewModel.memberData.collectAsState()
    var isShowAddressDialog by remember { mutableStateOf(false)  }

    // 회원검색 받기
    // Scan 한 후 전달받은 BarCode
    navController.currentBackStackEntry?.savedStateHandle?.getStateFlow(MEMBER_DATA, MemberData())
        ?.collectAsState()?.let { memberData ->
            LaunchedEffect(
                key1 = memberData,
                block = {
                    viewModel.setMemberData(memberData.value)
                }
            )
        }


    Scaffold(topBar = {
        NavigationAppBar(
            title = "회원 관리",
            onClickBack = { navController.popBackStack() })
    }) {
        MemberManagerContent(
            onClickAddress = {
                isShowAddressDialog = true
            },
            memberData = memberData ?: MemberData(),
            onChangValue = { viewModel.setMemberData(it)},
            onClickSave = { memberData ->
                viewModel.updateMemberData(memberData)
            },
            onClickSearch = {
                navController.navigate(Screen.MemberSearch.route)
            }
        )
    }

    if (isShowAddressDialog && memberData != null) {
        DaumAddressDialog(onFindAddress = { zipCode, roadAddress, _ ->
            val addressData = AddressData(
                zipCode = zipCode,
                address = roadAddress
            )
            viewModel.setMemberData(memberData!!.copy(personData = memberData!!.personData.copy(addressData = addressData)))
            isShowAddressDialog = false
        },
            onDismissDialog = { isShowAddressDialog = false }
        )
    }

}

