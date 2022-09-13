package kr.pe.paran.library_app.screen.member.member_home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.screen.member.member_card_screen.MemberCardContent
import kr.pe.paran.library_app.screen.member.member_card_screen.MemberCardScreen
import kr.pe.paran.library_app.screen.member.member_loaned_screen.MemberLoanedContent
import kr.pe.paran.library_app.screen.member.member_loaned_screen.MemberLoanedScreen
import kr.pe.paran.library_app.screen.member.member_reserved_screen.MemberReservedContent
import kr.pe.paran.library_app.screen.member.member_reserved_screen.MemberReservedScreen

@ExperimentalPagerApi
@Composable
fun MemberHomeContent(
    navController: NavController
) {
    val pagerState = rememberPagerState()


    Column(modifier = Modifier.fillMaxWidth()) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navController = navController)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {

    val list = listOf(
        "도서검색" to Icons.Default.Bookmark,
        "보유도서" to Icons.Default.Book,
        "회원카드" to Icons.Default.CreditCard
    )

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                icon = {
                    Icon(imageVector = list[index].second, contentDescription = null)
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, navController: NavController) {
    HorizontalPager(state = pagerState, count = 3) { page ->
        when (page) {
            0 -> MemberReservedScreen(navController = navController)
            1 -> MemberLoanedScreen(navController = navController)
            2 -> MemberCardScreen(navController = navController)
        }
    }
}

