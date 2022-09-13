package kr.pe.paran.library_app.screen.login_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.type.AccountType
import kr.pe.paran.library_app.navigation.Screen
import kr.pe.paran.library_app.network.NetworkStatus

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val networkStatus by viewModel.networkStatus.collectAsState()
    var isMemberType by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LoginContent(
        onClickLogin = { isMember, userid, userpw ->
            isMemberType = isMember
            val accountData = AccountData(
                userid = userid,
                userpw = userpw,
                accountType = if (isMember) AccountType.MEMBER else AccountType.LIBRARIAN
            )
            viewModel.login(accountData = accountData)
        }
    )

    if (networkStatus is NetworkStatus.SUCCESS) {
        if (isMemberType) {
            val memberData = (networkStatus as NetworkStatus.SUCCESS).data as MemberData
            viewModel.setMemberData(memberData)
            LaunchedEffect(key1 = Unit, block = {
                navController.popBackStack()
                navController.navigate(Screen.MemberHome.route)

            })
        } else {
            val librarianData = (networkStatus as NetworkStatus.SUCCESS).data as LibrarianData
            viewModel.setLibrarianData(librarianData)
            LaunchedEffect(key1 = Unit, block = {
                navController.popBackStack()
                navController.navigate(Screen.LibrarianHome.route)
            })
        }
    } else if (networkStatus is NetworkStatus.FAILURE) {
        Toast.makeText(context, "LOGIN FAILURE", Toast.LENGTH_SHORT).show()
    }
}