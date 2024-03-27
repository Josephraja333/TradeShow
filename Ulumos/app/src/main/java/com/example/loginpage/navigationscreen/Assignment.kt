package com.example.loginpage.navigationscreen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.API.AssignData
import com.example.loginpage.API.Assignment
import com.example.loginpage.API.Subjects
import com.example.loginpage.API.SubjectsData
import com.example.loginpage.R
import com.example.loginpage.viewModels.ProfileViewModel
import kotlinx.coroutines.runBlocking
import java.util.Locale

var assignments : List<Assignment>? = emptyList()
var subjects : List<Subjects>? = emptyList()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Assignment(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel){
    var clickedIndex by rememberSaveable { mutableIntStateOf(0) }
    val data by profileViewModel.assignResponse.collectAsState()
    val subject by profileViewModel.subjectsResponse.collectAsState()
    val lines = listOf(R.drawable.line, R.drawable.line_2, R.drawable.line_3, R.drawable.line_4)

    runBlocking {
        assign(clickedIndex,subject,data,profileViewModel)
    }


    Surface(
        modifier = modifier.background(Color(0xFF848282))
    ){
        Column {
            Column(modifier = Modifier
                .fillMaxWidth()){
                Text(text = "Assignment",modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, start = 24.dp),textAlign = TextAlign.Start, fontWeight = FontWeight.Bold, fontSize = 36.sp, color = Color.Black)
            }
            LazyRow(modifier = Modifier.padding(4.dp)){
                itemsIndexed(subjects.orEmpty()) { index, subject ->
                    val backgroundColor =
                        if (index == clickedIndex) Color(0xFF3498DB) else Color.White
                    val textColor =
                        if (index == clickedIndex) Color.White else  Color(0xFF3498DB)
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = subject.subjectName,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            color = textColor,
                            modifier = Modifier
                                .clickable {
                                    clickedIndex = index
                                }
                                .clip(CircleShape)
                                .background(backgroundColor)
                                .border(1.dp, Color(0xFF3498DB), CircleShape)
                                .padding(6.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
            val sheetState = rememberModalBottomSheetState()

            LazyColumn {
                assignments?.forEach {

                    item {
                        var isOpen by rememberSaveable { mutableStateOf(false) }

                        val l = when(it.assignmentStatus.toString().lowercase(Locale.ROOT)){
                            "null"->lines[1]
                            "gp"->lines[2]
                            "cp"->lines[3]
                            else -> {lines[0]}
                        }

                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 3.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(5),
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable { isOpen = true },
                        ) {

                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.padding(0.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = l),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(
                                            top = 0.dp,
                                            bottom = 0.dp,
                                            start = 0.dp,
                                            end = 0.dp
                                        )
                                        .size(77.dp)
                                )
                                Column(
                                    modifier = Modifier.padding(0.dp)
                                ) {
                                    Text(text = it.assignmentName, fontWeight = FontWeight.Bold, color = Color.Black)
                                    Text(text = it.facultyName, fontWeight = FontWeight.Thin, color = Color.Black)
                                }

                            }
                        }
                        if(isOpen) {
                            ModalBottomSheet(sheetState = sheetState, onDismissRequest = {isOpen = false}, containerColor = Color.White, contentColor = Color.Black) {
                                LazyColumn{
                                    item {
                                        Column (modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                            .padding(16.dp)){
                                            Text(text = it.assignmentName,modifier = Modifier, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                                            Text(text = "Submission Date - "+it.deadline, fontWeight = FontWeight.Bold,modifier = Modifier.padding(top=6.dp))
                                        }

                                        Text(text = it.assignmentDescription,modifier = Modifier
                                            .fillMaxWidth().background(Color.White)
                                            .padding(16.dp))

                                        Row(modifier = Modifier
                                            .background(Color.White)
                                            .fillMaxWidth().padding(6.dp)){
                                            it.prerequisiteAssignments.forEach {
                                                Text(text = it,
                                                    textAlign = TextAlign.Center, color =  Color(0xFFFFFFFF),
                                                    modifier = Modifier
                                                        .clip(CircleShape)
                                                        .background(Color(0xFF00B65E))
                                                        .padding(6.dp)
                                                )
                                            }
                                        }

                                        Row(modifier = Modifier
                                            .background(Color(0xFFFFFFFF))
                                            .fillMaxWidth()
                                            .weight(1f)) {
                                            if (it.assignmentStatus.toString()
                                                    .lowercase(Locale.ROOT) == "null"
                                            ) {
                                                Card(colors = CardDefaults.cardColors(
                                                    containerColor = Color(0xFF00B65E)
                                                ),
                                                    shape = RoundedCornerShape(50),
                                                    modifier = Modifier
                                                        .padding(12.dp)
                                                        .weight(1f)
                                                        .clickable {
                                                            runBlocking {
                                                                profileViewModel.postDataToStatusAPI(
                                                                    "1",
                                                                    it.assignmentId.toString(),
                                                                    it.subjectId.toString()
                                                                )
                                                                assign(clickedIndex,subject,data,profileViewModel)
                                                            }
                                                        }) {
                                                    Text(
                                                        text = "BEGIN",
                                                        modifier = Modifier
                                                            .padding(4.dp)
                                                            .fillMaxWidth(),
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center,
                                                        color = Color(0xFFFFFFFF)
                                                    )
                                                }
                                            }
                                            if (it.assignmentStatus.toString().lowercase(Locale.ROOT) == "ip"){
                                                Card(
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = Color(0xFF4CAF50)
                                                    ),
                                                    shape = RoundedCornerShape(50),
                                                    modifier = Modifier
                                                        .padding(12.dp)
                                                        .weight(1f)
                                                ) {
                                                    Text(text = "SUBMIT",
                                                        modifier = Modifier
                                                            .padding(4.dp)
                                                            .fillMaxWidth()
                                                            .clickable {
                                                                runBlocking {
                                                                    profileViewModel.postDataToCompleteAPI(
                                                                        it.assignmentId.toString(),
                                                                        it.subjectId.toString(),
                                                                        "nothing"
                                                                    )
                                                                    assign(clickedIndex,subject,data,profileViewModel)
                                                                }
                                                            },
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center,
                                                        color = Color(0xFFFFFFFF)
                                                    )
                                                }
                                            }
                                            if (it.assignmentStatus.toString().lowercase(Locale.ROOT) == "null" || it.assignmentStatus.toString().lowercase(
                                                    Locale.ROOT) == "ip"){
                                                Card(
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = Color(
                                                            0xFFFFFFFF
                                                        )
                                                    ),
                                                    shape = RoundedCornerShape(50),
                                                    modifier = Modifier
                                                        .padding(12.dp)
                                                        .weight(1f)
                                                        .border(1.dp,Color(0xFF00B65E), CircleShape)
                                                ) {
                                                    Text(text = "GIVE UP",
                                                        modifier = Modifier
                                                            .padding(4.dp)
                                                            .fillMaxWidth()
                                                            .clickable {
                                                                runBlocking {
                                                                    profileViewModel.postDataToStatusAPI(
                                                                        "2",
                                                                        it.assignmentId.toString(),
                                                                        it.subjectId.toString()
                                                                    )
                                                                    assign(clickedIndex,subject,data,profileViewModel)
                                                                }
                                                            },
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center,
                                                        color = Color(0xFF00B65E)
                                                    )
                                                }
                                            }
                                            if (it.assignmentStatus.toString().lowercase(Locale.ROOT) != "null" && it.assignmentStatus.toString().lowercase(
                                                    Locale.ROOT) != "ip"){

                                                Card(
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = Color(0xFFFFFFFF)
                                                    ),
                                                    shape = RoundedCornerShape(50),
                                                    modifier = Modifier
                                                        .padding(12.dp)
                                                        .weight(1f)
                                                        .border(1.dp,Color(0xFF00B65E), CircleShape)
                                                ) {
                                                    Text(text = "CLOSE",
                                                        modifier = Modifier
                                                            .padding(4.dp)
                                                            .fillMaxWidth()
                                                            .clickable {
                                                                isOpen = false
                                                            },
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center,
                                                        color = Color(0xFF00B65E)
                                                    )
                                                }
                                            }

                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(35.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}


suspend fun assign(clickedIndex: Int, subject: SubjectsData?, data: AssignData?, profileViewModel : ProfileViewModel){
        profileViewModel.fetchDataFromSubjectsAPI()
        profileViewModel.fetchDataFromAssignAPI((clickedIndex+1).toString())
        subjects = subject?.response
        assignments = data?.response
}