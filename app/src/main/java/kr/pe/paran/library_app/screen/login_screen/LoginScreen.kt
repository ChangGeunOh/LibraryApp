package kr.pe.paran.library_app.screen.login_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.network.NetworkStatus

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val networkStatus by viewModel.networkStatus.collectAsState()
    val context = LocalContext.current

    LoginContent(
        onClickLogin = { isMember, userid, userpw ->
            val accountData = AccountData(userid = userid, userpw = userpw)
            if (isMember) {
                viewModel.login(accountData = accountData)
            } else {
                viewModel.loginLibrarian(accountData = accountData)
            }
        }
    )

    if (networkStatus is NetworkStatus.SUCCESS) {
        if ((networkStatus as NetworkStatus.SUCCESS).data is MemberData) {
            val memberData = (networkStatus as NetworkStatus.SUCCESS).data as MemberData
            if (memberData.id != 0) {
                Toast.makeText(context, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "LOGIN FAILURE", Toast.LENGTH_SHORT).show()
            }
            Log.i(":::::", "MemberData>${memberData.toString()}")
        } else if ((networkStatus as NetworkStatus.SUCCESS).data is LibrarianData) {
            val librarianData = (networkStatus as NetworkStatus.SUCCESS).data as LibrarianData
            if (librarianData.id != 0) {
                Toast.makeText(context, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "LOGIN FAILURE", Toast.LENGTH_SHORT).show()
            }
            Log.i(":::::", "Librarian>${librarianData.toString()}")
        }
    } else if (networkStatus is NetworkStatus.FAILURE) {
        Toast.makeText(context, "NETWORK FAILURE", Toast.LENGTH_SHORT).show()
    }
}