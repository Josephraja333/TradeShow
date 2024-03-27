package com.example.loginpage.navigationscreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpage.ProgressBar
import com.example.loginpage.viewModels.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Dash(modifier: Modifier = Modifier,viewModel: ProfileViewModel,navController: NavHostController){
    val data by viewModel.dashResponse.collectAsState()
    val subjects = data?.details
    val time by viewModel.timeResponse.collectAsState()
    val table = time?.response
    val dat by viewModel.userResponse.collectAsState()
    val da = dat?.firstOrNull()

    LaunchedEffect(navController) {
        delay(5000)
        if (data?.assignments?.totalAssignments.toString() == "null") {
            navController.navigate("networkError")
        }
    }
    runBlocking {
        viewModel.fetchDataFromDashAPI()
    }
    Surface(
        modifier = modifier.background(Color(0xFFFAFAFA))
    ) {
        LazyColumn {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF0F0F0))
                    .padding(16.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.BottomStart)){
                            Text(
                                text = "Hello,",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .align(Alignment.Start),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = if(da?.username.toString()!="null")da?.username.toString() else "loading",
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                                    ,
                                textAlign = TextAlign.Start,
                                fontSize = 24.sp
                            )
                        }
//                        Icon(
//                            imageVector = Icons.Filled.NotificationsNone,
//                            contentDescription = "Notifications",
//                            modifier = Modifier
//                                .size(25.dp)
//                                .padding(end = 0.dp)
//                                .align(Alignment.CenterEnd),
//                            tint = Color(0xFF3498DB)
//                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 0.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFFFFF)
                        ),
                        shape = RoundedCornerShape(10),
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Box(modifier = Modifier.padding(16.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.CenterStart)
                            ) {
                                Icon(imageVector = Icons.Outlined.TaskAlt, contentDescription = null,modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(8.dp), tint = Color(0xFF00B65E))
                                Text(
                                    text = if(data?.assignments?.completedAssignments.toString()!="null")data?.assignments?.completedAssignments.toString() else "loading",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 35.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Completed",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.Start)
                                )
                            }
                        }
                    }

                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 0.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFFFFF)
                        ),
                        shape = RoundedCornerShape(10),
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Box(modifier = Modifier.padding(16.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.CenterStart)
                            ) {
                                Icon(imageVector = Icons.Outlined.Timelapse, contentDescription = null,modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(8.dp), tint = Color(0xFFFF9800)
                                )
                                Text(
                                    text = if(data?.assignments?.incompleteAssignments.toString()!="null")data?.assignments?.incompleteAssignments.toString() else "loading",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 35.sp,
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "Pending",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.Start)
                                )
                            }
                        }
                    }

                }
            }

            item {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFFFF)
                    ),
                    shape = RoundedCornerShape(5),
                    modifier = Modifier
                        .padding(18.dp)
                ) {
                    Text(
                        text = "Statistics",
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                Spacer(modifier = Modifier.padding(6.dp))

                    subjects?.forEach {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = it.subjectName,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 8.dp, start = 8.dp)
                                    .align(Alignment.CenterStart)
                            )
                            Text(text = "${it.subjectCompleted}/${it.count}", color = Color.Black, modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 4.dp))
                        }
                        Spacer(modifier = Modifier.padding(3.dp))
                        ProgressBar(((it.subjectCompleted.toFloat() / it.count.toFloat())))
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                    Spacer(modifier = Modifier.padding(6.dp))
                }
            }

            item {
                    Text(
                        text = "Time Table",
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.ExtraBold
                    )

                    table?.forEach{
                        var s = " "
                        if(it.time[1]!= ' ') s = it.time
                        else {
                            val b = it.time.split(" ")
                            s = b.first()+":00 "+b.last()
                        }
                        TimeTable(it.className,s)
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

            }
        }
    }
}

@Composable
fun TimeTable(s: String,t: String){
    val tie = SimpleDateFormat("yyyy-MM-dd hh:mm a").format(Calendar.getInstance().time).split(" ")
    val time = tie[1] +" "+ tie.last()
    var color = Color(0xFFFFFFFF)

    val sdf = SimpleDateFormat("hh:mm a")
    val calendar = Calendar.getInstance()
    calendar.time = sdf.parse(t)
    calendar.add(Calendar.MINUTE, 45)
    val newTime = sdf.format(calendar.time)

    val date1 = SimpleDateFormat("hh:mm a").parse(time)
    val date2 = SimpleDateFormat("hh:mm a").parse(t)
    val date3 = SimpleDateFormat("hh:mm a").parse(newTime)
    Log.i("time",date2.toString())

    if(date2.before(date1)) {
        color = Color(0xFFAAAAAA)
    }

    if(date2.before(date1) && date1.before(date3) ) {
        color = Color(0xFFDBEEFC)
    }

    if(t.contains("-")){
        color = Color(0xFFDBEEFC)
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(10),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 0.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ){
        Row(modifier = Modifier.padding(18.dp)){
            Column{
                Text(text = t, fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.Black)
            }
            Column(modifier = Modifier.fillMaxWidth().padding(start = 26.dp)){
                Text(text = s, textAlign = TextAlign.Start,modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally), color = Color.Black)
            }
        }
    }
}