package com.example.loginpage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(a : Float){
    var progress by remember { mutableFloatStateOf(0f) }

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        ), label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(12.dp)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(size)
                .fillMaxHeight()
                .clip(RoundedCornerShape(9.dp))
                .background(Color(0xFF3498DB))
                .animateContentSize(),
        ){

        }
    }

    LaunchedEffect(key1 = true) {
        progress = a
    }
}