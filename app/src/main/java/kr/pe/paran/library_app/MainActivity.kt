package kr.pe.paran.library_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kr.pe.paran.library_app.navigation.NavGraph
import kr.pe.paran.library_app.ui.theme.LibraryAppTheme

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryAppTheme {
                val navHostController = rememberNavController()
                NavGraph(navHostController = navHostController)
            }
        }
    }
}

