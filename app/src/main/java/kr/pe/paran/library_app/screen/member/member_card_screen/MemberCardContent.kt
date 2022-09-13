package kr.pe.paran.library_app.screen.member.member_card_screen

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.R
import kr.pe.paran.library_app.model.AccountData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.model.PersonData
import kr.pe.paran.library_app.utils.Utils

@Preview(showBackground = true)
@Composable
fun MemberCardContent(
    memberData: MemberData = MemberData(
        id = 1,
        personData = PersonData(name = "진지현"),
        cardNumber = "1234567890",
        barCode = "1234-4567-8901-1234",
        accountData = AccountData()
    )
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.align(Alignment.Center),
            border = BorderStroke(2.dp, Color.LightGray),
            elevation = 5.dp,
            shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_card_background),
                contentDescription = null,
            )

            Text(
                text = "도서관 회원증",
                color = Color.Black,
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color.White)
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            ) {
                Utils.getBarCode(memberData.barCode)?.let { bitmap: Bitmap ->
                    Image(
                        modifier = Modifier
                            .weight(1f),
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "barCode",
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(vertical = 2.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "회원번호 : ${memberData.cardNumber}", fontSize = 12.sp)
                    Text(text = "회원이름 : ${memberData.personData.name}", fontSize = 12.sp)
                }
            }
        }
    }
}


/*

 */