package kr.pe.paran.library_app.screen.splash_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashContent(paddingValues: PaddingValues) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text("Splash Screen")
    }
}