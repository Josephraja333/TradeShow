package com.example.loginpage.navigationscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpage.R
import com.example.loginpage.viewModels.ProfileViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun Profiles(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel, navController: NavHostController){
    val dat by profileViewModel.userResponse.collectAsState()
    val data = dat?.firstOrNull()
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Surface(modifier = modifier.background(Color(0xFF848282))){

        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                Column(modifier = Modifier.background(Color.White)){
                    Text(
                        text = "Profile",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            item {
                Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ram),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(68.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterStart)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(start = 28.dp)
                    ) {
                        Text(
                            text = data?.username.toString(),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = data?.zohoCorpMail.toString(),
                            textAlign = TextAlign.Start,
                            color = Color(0xFF919195)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(60.dp), verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "EMAIL ID",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                fontSize = 12.sp,
                                color = Color(0xFF919195)
                            )
                        }
                        Text(
                            text = data?.zohoCorpMail.toString(), modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp), color = Color.Black
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(48.dp), verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "CORP ID",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                fontSize = 12.sp,
                                color = Color(0xFF919195)
                            )
                        }
                        Text(
                            text = data?.zohoCorpId.toString(), modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp), color = Color.Black
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(48.dp), verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "REPORTING TO",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                fontSize = 12.sp,
                                color = Color(0xFF919195)
                            )
                        }
                        Text(
                            text = "Uma Maheswari R", modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp), color = Color.Black
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(48.dp), verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "SCHOOL",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                fontSize = 12.sp,
                                color = Color(0xFF919195)
                            )
                        }
                        Text(
                            text = data?.schoolName.toString(), modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp), color = Color.Black
                        )
                    }
                }
                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .height(56.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .background(Color.White)
                                .align(Alignment.CenterStart)
                                .clickable {
                                    showDialog = true
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PowerSettingsNew,
                                contentDescription = null,
                                tint = Color(0xFFE14C4C)
                            )
                            
                            Text(text = "Sign out", color = Color(0xFFE14C4C), modifier = Modifier.padding(start = 8.dp), textAlign = TextAlign.Start)
                        }
                    }
                    if (showDialog) {
                        LogoutDialog(
                            onDismiss = { showDialog = false },
                            onLogout = { runBlocking { profileViewModel.deleteCredentialsFromDataStore() }
                                navController.navigate("onBoarding") }
                        )
                    }
            }
            }
        }
    }
}