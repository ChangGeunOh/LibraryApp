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

       // 우편번호 찾기 찾기 화면을 넣을 element
        var element_wrap = document.getElementById('wrap');

            // 현재 scroll 위치를 저장해놓는다.
            var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);

            new daum.Postcode({
                oncomplete: function(data) {

                    var zipCode = data.zonecode;
                    
                    var roadAddr = "";
                    var landAddr = "";
                    var extraAddr = ''; // 참고항목 변수

                    // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    // 사용자 선택 구분 (도로명선택) : data.userSelectedType === 'R' 
                    roadAddr = data.roadAddress;
                    landAddr = data.jibunAddress;

                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]${'$'}/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    
                    if (extraAddr !== '') {
                        roadAddr += ' ' + extraAddr; 
                    }

                    Android.findAddress(zipCode, roadAddr, landAddr);

                    // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                    document.body.scrollTop = currentScroll;
                },
                // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
                onresize : function(size) {
                    element_wrap.style.height = size.height+'px';
                },
                width : '100%',
                height : '100%'
            }).embed(element_wrap);

            // iframe을 넣은 element를 보이게 한다.
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