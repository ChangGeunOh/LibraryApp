package kr.pe.paran.library_app.screen.scanner_screen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScannerContent(
    onReadCode: (String) -> Unit
) {
    val context = LocalContext.current
    var scanFlag by remember { mutableStateOf(false) }
    var readedCode by remember { mutableStateOf("") }

    val compoundBarcodeView = remember {
        CompoundBarcodeView(context).apply {
            val capture = CaptureManager(context as Activity, this)
            capture.initializeFromIntent(context.intent, null)
            this.setStatusText("바코드를 사각형 안에 비춰주세요.")
            capture.decode()
            this.decodeContinuous { result ->
                if (scanFlag) {
                    return@decodeContinuous
                }
                scanFlag = true
                result.text?.let { barCodeOrQr ->
                    if (barCodeOrQr != readedCode) {
                        onReadCode(barCodeOrQr)
                    }
                    readedCode = barCodeOrQr
                }
            }
            this.resume()
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { compoundBarcodeView },
    )
}