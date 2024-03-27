package com.example.loginpage

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.loginpage.navigationscreen.LoginPage
import com.example.loginpage.navigationscreen.MyApp
import com.example.loginpage.navigationscreen.OnBoarding
import com.example.loginpage.navigationscreen.Error
import com.example.loginpage.ui.theme.LoginPageTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginpage.dataStore.DataStoreManager
import com.example.loginpage.viewModels.ProfileViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageTheme {
                Column {
                    val profileViewModel: ProfileViewModel = viewModel()
                    val navController = rememberNavController()
                    val dataStoreManager = DataStoreManager.getInstance(LocalContext.current)
                    val hasLoggedIn = dataStoreManager.hasLoggedIn
                    var s = "onBoarding"
                    if(hasLoggedIn){
                        profileViewModel.fetchDataFromAPI(dataStoreManager.mailId, dataStoreManager.password,dataStoreManager)

                        s = "homePage"
                    }


                    val navGraph = remember(navController) {
                        navController.createGraph(startDestination = s) {
                            composable("onBoarding") {
                                OnBoarding(navController)
                            }

                            composable("loginPage") {
                                LoginPage(navController,profileViewModel,dataStoreManager)
                            }
                            composable("homePage") {
                                val windowSizeClass = calculateWindowSizeClass(this@MainActivity)
                                MyApp(windowSizeClass,profileViewModel,navController)
                            }
                            composable("networkError"){
                                Error(modifier = Modifier)
                            }
                        }
                    }
                    NavHost(navController = navController, graph = navGraph)
                }
            }
        }
    }
}