package kr.pe.paran.library_app.screen.splash_screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.library_app.R

@Preview(showBackground = true)
@Composable
fun SplashContent(paddingValues: PaddingValues = PaddingValues()) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_under_construction),
            contentDescription = "under construction"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .wrapContentWidth(),
            text = "Splash Screen",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}