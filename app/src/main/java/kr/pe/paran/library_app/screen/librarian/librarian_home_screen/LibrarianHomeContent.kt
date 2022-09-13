package kr.pe.paran.library_app.screen.librarian.librarian_home_screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.screen.librarian.LibrarianViewModel
import kr.pe.paran.library_app.screen.librarian.librarian_card_screen.LibrarianCardContent
import kr.pe.paran.library_app.screen.librarian.librarian_card_screen.LibrarianCardScreen
import kr.pe.paran.library_app.screen.librarian.librarian_menu_screen.LibrarianMenuContent
import kr.pe.paran.library_app.screen.librarian.librarian_menu_screen.LibrarianMenuScreen
import kr.pe.paran.library_app.ui.theme.Brown

@ExperimentalPagerApi
@Composable
fun LibrarianHomeContent(
    onClickMenu: (Int) -> Unit,
    librarianData: LibrarianData
) {
    val pagerState = rememberPagerState(initialPage = 0)
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = 2,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            if (index == 0) {
                LibrarianMenuContent(onClickMenu = {
                    onClickMenu(it)
                })
            } else {
                LibrarianCardContent(
                    librarianData = librarianData
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}