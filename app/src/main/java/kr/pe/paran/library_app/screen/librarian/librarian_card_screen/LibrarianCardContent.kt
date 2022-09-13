package kr.pe.paran.library_app.screen.librarian.librarian_card_screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.PersonData
import kr.pe.paran.library_app.utils.Utils

@Preview(showBackground = true)
@Composable
fun LibrarianCardContent(
    librarianData: LibrarianData = LibrarianData(
        id = 1,
        personData = PersonData(name = "전지현", email = "fiveroot@paran.com", phone = "010-1234-7890"),
        cardNumber = "1234567890",
        barCode = "1234-4567-8901-1234",
        accountData = AccountData()
    )
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.img_libraian_card_background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 135.dp, start = 64.dp, end = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.img_avatar),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape)
                    .border(width = 4.dp, color = Color.Gray, shape = CircleShape)
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = librarianData.personData.name,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color(0xff1b4865)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Librarian Manager",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xff1b8acf)
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "ID No : ${librarianData.cardNumber}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xff1b4865)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Email : ${librarianData.personData.email}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xff1b4865)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Phone : ${librarianData.personData.phone}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xff1b4865)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Utils.getBarCode(librarianData.barCode)?.let { bitmap: Bitmap ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "barCode",
                    contentScale = ContentScale.Crop
                )
            }
        }

    }

}