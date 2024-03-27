package com.example.loginpage.navigationscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginpage.dataStore.DataStoreManager
import com.example.loginpage.viewModels.ProfileViewModel

@Composable
fun Login(navController : NavHostController, viewModel: ProfileViewModel, datastoreManger: DataStoreManager, modifier: Modifier = Modifier){
    val navigator : NavHostController = navController
    var c by remember{ mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf( "") }
    var password by rememberSaveable { mutableStateOf( "") }
    var chk by rememberSaveable { mutableStateOf( false) }
    var visible by rememberSaveable { mutableStateOf( false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Box(
            modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Text("Log in to your\n\nAccount",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(top = 26.dp)
                    .align(Alignment.TopStart)
            )
        }

        Column(
            modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)) {



            OutlinedTextField(value = email,
                onValueChange = { newText -> email = newText},
                singleLine = true,
                label = { Text(text = "Email ID") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Email,
                        contentDescription = "Email Icon")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF3498DB),
                    unfocusedBorderColor = Color(0xFF3498DB),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            if (email.contains(".com") && email.contains("@")) chk = true

            OutlinedTextField(value = password,
                onValueChange = { newText -> password = newText},
                singleLine = true,
                label = { Text(text = "Password") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock,
                        contentDescription = "Lock Icon")
                },
                visualTransformation =  PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF3498DB),
                    unfocusedBorderColor = Color(0xFF3498DB),
                ),
                enabled = chk,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            if(visible) Box(modifier = Modifier.fillMaxWidth()){ Text(text = "Please Enter all the Informations above", color = Color.Red, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center), textAlign = TextAlign.Center)}

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        if (email.isNotEmpty() && password.isNotEmpty()) c = true else visible =
                            true
                    },colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3498DB),

                    )){
                Text(text = "Login",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(24.dp)
                        .align(Alignment.CenterHorizontally),
                    fontSize = 24.sp)
            }

        }

    }


    if(c) {
         viewModel.fetchDataFromAPI(email, password, datastoreManger)
         navigator.popBackStack("onBoarding", true)
         navigator.navigate("homePage")
    }
}