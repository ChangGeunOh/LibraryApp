package kr.pe.paran.library_app.screen.member.member_home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import kr.pe.paran.library_app.screen.member.MemberViewModel

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MemberHomeScreen(
    navController: NavController,
    viewModel: MemberViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = { Text("Genie 도서관") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                }
            )
        }
    ) {
        MemberHomeContent()
    }
}