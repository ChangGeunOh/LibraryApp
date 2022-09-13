package kr.pe.paran.library_app.screen.librarian.librarian_menu_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.ui.theme.Brown

@Composable
fun LibrarianMenuContent(
    onClickMenu: (Int) -> Unit = {}
) {

    val list = listOf(
        "신규서적" to 1,
        "구매서적" to 2,
        "도서대여" to 3,
        "도서반납" to 4,
        "도서관리" to 5,
        "회원관리" to 6
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Genie 도서관",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Brown,
        )
        Spacer(modifier = Modifier.height(32.dp))
        RowMenuItems(list[0], list[1], onClickMenu = { onClickMenu(it) })
        Spacer(modifier = Modifier.height(24.dp))
        RowMenuItems(list[2], list[3], onClickMenu = { onClickMenu(it) })
        Spacer(modifier = Modifier.height(24.dp))
        RowMenuItems(list[4], list[5], onClickMenu = { onClickMenu(it) })
    }
}


@Composable
fun RowMenuItems(
    firstMenuItem: Pair<String, Int>,
    secondMenuItem: Pair<String, Int>,
    onClickMenu: (Int) -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        MenuItem(
            menuData = firstMenuItem,
            onClickMenu = { onClickMenu(it) }
        )
        Spacer(modifier = Modifier.width(50.dp))
        MenuItem(
            menuData = secondMenuItem,
            onClickMenu = { onClickMenu(it) }
        )
    }
}

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menuData: Pair<String, Int>,
    onClickMenu: (Int) -> Unit
) {

    TextButton(
        modifier = modifier
            .width(100.dp)
            .height(100.dp)
            .background(Brown, shape = RoundedCornerShape(8.dp)),
        onClick = { onClickMenu(menuData.second) }
    ) {
        Text(
            text = menuData.first,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
    }

}