package kr.pe.paran.library_app.component

import android.webkit.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DaumAddressDialog(
    modifier: Modifier = Modifier.padding(vertical = 64.dp).fillMaxSize(),
    onFindAddress: (String, String, String) -> Unit,
    onDismissDialog: () -> Unit
) {

    Dialog(
        onDismissRequest = { onDismissDialog() },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        AndroidView(factory = {

            WebView(it).apply {

                settings.apply {
                    javaScriptEnabled = true
                    cacheMode = WebSettings.LOAD_DEFAULT

                    setSupportZoom(false)
                    builtInZoomControls = false
                    displayZoomControls = false

                    blockNetworkImage = false
                    loadsImagesAutomatically = true

                    useWideViewPort = true
                    loadWithOverviewMode = true
                    javaScriptCanOpenWindowsAutomatically = true
                    mediaPlaybackRequiresUserGesture = false

                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    allowContentAccess = true
                    setGeolocationEnabled(true)
                    allowFileAccess = true
                }
                fitsSystemWindows = true

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return false
                    }
                }

                addJavascriptInterface(
                    WebAppInterface(onClickAddress = { zipCode, roadAddr, landAddr ->
                        onFindAddress(zipCode, roadAddr, landAddr)
                    }
                    ), "Android")
            }
        }, update = {
            it.loadDataWithBaseURL(
                "http://tuduri.pe.kr",
                htmlDaumAddress,
                "text/html",
                "utf-8",
                null
            )
        },
            modifier = modifier
        )
    }

}

private val htmlDaumAddress = """
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport"
              content="width=device-width, initial-scale=0.8, maximum-scale=0.8, minimum-scale=0.8, user-scalable=no ">
    </head>
    <body>

    <div id="wrap"></div>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>

       // ???????????? ?????? ?????? ????????? ?????? element
        var element_wrap = document.getElementById('wrap');

            // ?????? scroll ????????? ??????????????????.
            var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);

            new daum.Postcode({
                oncomplete: function(data) {

                    var zipCode = data.zonecode;
                    
                    var roadAddr = "";
                    var landAddr = "";
                    var extraAddr = ''; // ???????????? ??????

                    // ???????????? ????????? ?????? ????????? ?????? ?????? ?????? ?????? ????????????.
                    // ????????? ?????? ?????? (???????????????) : data.userSelectedType === 'R' 
                    roadAddr = data.roadAddress;
                    landAddr = data.jibunAddress;

                    // ??????????????? ?????? ?????? ????????????. (???????????? ??????)
                    // ???????????? ?????? ????????? ????????? "???/???/???"??? ?????????.
                    if(data.bname !== '' && /[???|???|???]${'$'}/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    
                    // ???????????? ??????, ??????????????? ?????? ????????????.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    
                    // ????????? ??????????????? ?????? ??????, ???????????? ????????? ?????? ???????????? ?????????.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    
                    if (extraAddr !== '') {
                        roadAddr += ' ' + extraAddr; 
                    }

                    Android.findAddress(zipCode, roadAddr, landAddr);

                    // ???????????? ?????? ????????? ????????? ???????????? scroll ????????? ????????????.
                    document.body.scrollTop = currentScroll;
                },
                // ???????????? ?????? ?????? ????????? ?????????????????? ????????? ????????? ???????????? ??????. iframe??? ?????? element??? ???????????? ????????????.
                onresize : function(size) {
                    element_wrap.style.height = size.height+'px';
                },
                width : '100%',
                height : '100%'
            }).embed(element_wrap);

            // iframe??? ?????? element??? ????????? ??????.
    <!--        element_wrap.style.display = 'block';-->


    </script>
    </body>
    </html>
""".trimIndent()

private class WebAppInterface(val onClickAddress: (String, String, String) -> Unit) {
    /* Show a toast from the web page  */
    @JavascriptInterface
    fun findAddress(zipCode: String, roadAddr: String, landAddr: String) {
        onClickAddress(zipCode, roadAddr, landAddr)
    }
}