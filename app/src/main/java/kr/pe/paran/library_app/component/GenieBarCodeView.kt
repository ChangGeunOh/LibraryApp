package kr.pe.paran.library_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.utils.Utils

@Composable
fun GenieBarCodeView(barCode: String, label: String = "바코드", title: String = "", isShowShareIcon: Boolean) {

    val context = LocalContext.current

    Text(
        text = label,
        modifier = Modifier.padding(top = 4.dp, start = 8.dp, bottom = 4.dp),
        fontSize = 12.sp
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            bitmap = Utils.getBarCode(barCode)!!.asImageBitmap(),
            contentDescription = "BookItem BarCode",
            contentScale = ContentScale.Crop
        )
        if (isShowShareIcon) {
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = {
                    Utils.shareBarCode(
                        context = context,
                        barCode = barCode,
                        title = title
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "BarCode Share",
                    tint = Color.Gray
                )
            }
        }
    }
}