package com.example.loginpage.navigationscreen

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.R
import com.example.loginpage.viewModels.ProfileViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun Video(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel){
    runBlocking {
        profileViewModel.fetchDataFromVideosAPI()
        profileViewModel.fetchDataFromSubjectsAPI()
    }

    val data by profileViewModel.videosResponse.collectAsState()
    val subject by profileViewModel.subjectsResponse.collectAsState()
    val subjects  = subject?.response
    val videos = data?.response

    Surface(
        modifier = modifier
    ) {
    Column {
            Column(
                modifier = Modifier
                    .background(Color(0xFFF0F0F0))
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Classes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp, start = 24.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.Black
                )
            }
        LazyColumn {
            subjects?.forEach {
                item {
                    Column {
                        Text(
                            text = it.subjectName,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 24.dp,top = 24.dp)
                        )
                        LazyRow {
                            videos?.forEach { video ->
                                    item {
                                        val openVideo = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
                                        if (it.subjectName == video.subjectName) {
                                            Column {
                                                ElevatedCard(
                                                    elevation = CardDefaults.cardElevation(
                                                        defaultElevation = 3.dp
                                                    ),
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = Color(0xFFFFFFFF)
                                                    ),
                                                    modifier = Modifier
                                                        .padding(start = 24.dp,14.dp)
                                                        .fillMaxWidth()
                                                        .height(180.dp)
                                                        .clickable {
                                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.videoLink))
                                                            openVideo.launch(intent)
                                                        }
                                                ) {
                                                    Image(painter = painterResource(id = listOf(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four).random()),contentDescription = "YoutubeVideo Thumbnail",modifier = Modifier.size(300.dp),contentScale = ContentScale.Crop)
                                                }
                                                Text(
                                                    text = video.subjectName,
                                                    modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 8.dp)
                                                )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    }
}