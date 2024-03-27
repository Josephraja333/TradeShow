package com.example.loginpage.navigationscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.loginpage.R

@Composable
fun Error(modifier: Modifier = Modifier){
    val doodle by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.errorg))
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
        LottieAnimation(
            composition = doodle,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Network Error",
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
    }
}