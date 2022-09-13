package kr.pe.paran.library_app.screen.scanner_screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kr.pe.paran.library_app.component.NavigationAppBar
import kr.pe.paran.library_app.screen.member.member_insert_screen.ShowToast
import kr.pe.paran.library_app.utils.Logcat
import kr.pe.paran.library_app.utils.Utils

@ExperimentalPermissionsApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScannerScreen(navController: NavController) {
    /*
        ISBN 바코드 : EAN-13으로 숫자만 사용할 수 있음
                텍스트 용량은 12자리 숫자 + 1자리 확인용 (13자리로 구성)
                ISBN979-11-6224-502-6

        이 프로젝트에서는 PDF417 코드 사용
            M 시작 : Memeber 바코드
            L 시작 : Librarian 바코드
            B 시작 : 구매 도서 바코드
                PDF417 사용 (사용 이유 : 인식율)
                * 문자 집합: 256자 모두 ASCII 문자
                * 코드 텍스트 용량: 최대 800자
     */

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    val context = LocalContext.current

    Scaffold(
        topBar = {
            NavigationAppBar(title = "Scan BarCode",
                onClickBack = { navController.popBackStack() })
        }
    ) {
        when (cameraPermissionState.status) {
            is PermissionStatus.Denied -> {
                cameraPermissionState.launchPermissionRequest()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {  Utils.showApplicationSettings(context = context) }
                    ) {
                        Text("카메라 권한 설정")
                    }
                }
            }
            PermissionStatus.Granted -> {
                ScannerContent(onReadCode = { code ->
                    Logcat.i("ReadCode>$code")
                    Toast.makeText(context, code, Toast.LENGTH_SHORT).show()
                    navController.previousBackStackEntry?.savedStateHandle?.set("code", code)
                    navController.popBackStack()
                })
            }
        }
    }
}
