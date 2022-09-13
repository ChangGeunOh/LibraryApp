package kr.pe.paran.library_app.screen.splash_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.navigation.Screen

@Composable
fun SplashScreen(navController: NavController) {
    Scaffold { paddingValues ->
        SplashContent(
            paddingValues = paddingValues
        )
    }

    LaunchedEffect(key1 = Unit, block = {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            navController.navigate(Screen.Login.route)
        }
    })
}