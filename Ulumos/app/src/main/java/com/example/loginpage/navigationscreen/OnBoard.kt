package com.example.loginpage.navigationscreen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpage.R

@Composable
fun OnBoard(navController : NavHostController, modifier: Modifier = Modifier){
    val activity = LocalContext.current as? Activity

    BackHandler(enabled = true){
        activity?.finish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(38.dp)) {
            Text("Learning",
                fontWeight = FontWeight.SemiBold,
                fontSize = 45.sp,
                modifier = Modifier
                    .align(Alignment.Start),
                color = Color(0xFF3498DB)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text("is the eye of the",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text("mind",
                fontWeight = FontWeight.SemiBold,
                fontSize = 45.sp,
                modifier = Modifier
                    .align(Alignment.Start),
                color = Color(0xFF3498DB)
            )
        }
        Box(
            modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.onboarding),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3498DB)
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 24.dp, end = 8.dp)
                    .clickable { navController.navigate("loginPage") }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 30.dp, start = 30.dp)) {
                    Text(
                        text = "Login",
                        color = Color(0xFFFFFFFF),
                        fontSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Rounded.KeyboardDoubleArrowRight,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}