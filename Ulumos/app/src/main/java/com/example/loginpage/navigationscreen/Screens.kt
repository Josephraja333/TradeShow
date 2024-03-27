package com.example.loginpage.navigationscreen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.loginpage.dataStore.DataStoreManager
import com.example.loginpage.ui.theme.LoginPageTheme
import com.example.loginpage.viewModels.ProfileViewModel

@Composable
fun OnBoarding(navController : NavHostController, modifier: Modifier = Modifier){
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    OnBoard(navController = navController, modifier = modifier)
}

@Composable
fun LoginPage(navController : NavHostController, viewModel: ProfileViewModel,datastoreManger: DataStoreManager,modifier: Modifier = Modifier){
    Login(navController = navController, viewModel = viewModel, datastoreManger = datastoreManger)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashBoard(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    navController: NavHostController
) {
    Dash(modifier = modifier,viewModel = viewModel,navController = navController)
}



@Composable
fun Assignments(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel
) {
    Assignment(modifier = modifier,profileViewModel = profileViewModel)
}

@Composable
fun ClassVideos(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel){
    Video(modifier = modifier,profileViewModel = profileViewModel)
}

@Composable
fun Profile(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel, navController: NavHostController){
    Profiles(modifier = modifier,profileViewModel = profileViewModel, navController = navController)
}

@Composable
private fun BottomNavigation(selectedItem: String, onItemSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    Bottom(selectedItem = selectedItem, onItemSelected = onItemSelected,modifier = modifier)
}

@Composable
private fun NavigationRail(selectedItem: String,onItemSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    Side(selectedItem = selectedItem, onItemSelected = onItemSelected,modifier = modifier)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppPortrait(
    selectedItem: String,
    setSelectedItem: (String) -> Unit,
    profileViewModel: ProfileViewModel,
    navController: NavHostController
) {
    LoginPageTheme {
        Scaffold(
            bottomBar = { BottomNavigation(selectedItem,setSelectedItem) }
        ) { scaffoldPadding ->
            when (selectedItem) {
                "dashBoard" -> DashBoard(modifier = Modifier.padding(scaffoldPadding),profileViewModel,navController)
                "assignments" -> Assignments(
                    modifier = Modifier.padding(scaffoldPadding),
                    profileViewModel = profileViewModel,
                )
                "classVideos" -> ClassVideos(
                    modifier = Modifier.padding(scaffoldPadding),
                    profileViewModel = profileViewModel
                )
                "profile" -> Profile(
                    modifier = Modifier.padding(scaffoldPadding),
                    profileViewModel = profileViewModel,
                    navController = navController
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppLandscape(
    selectedItem: String,
    setSelectedItem: (String) -> Unit,
    profileViewModel: ProfileViewModel,
    navController: NavHostController
) {
    LoginPageTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                NavigationRail(selectedItem,setSelectedItem)
                when (selectedItem) {
                    "dashBoard" -> DashBoard(modifier = Modifier, profileViewModel, navController)
                    "assignments" -> Assignments(modifier = Modifier,profileViewModel)
                    "classVideos" -> ClassVideos(modifier = Modifier,profileViewModel)
                    "profile" -> Profile(modifier = Modifier,profileViewModel,navController)
                }
            }
        }
    }
}


@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(
    windowSize: WindowSizeClass,
    profileViewModel: ProfileViewModel,
    navController: NavHostController,
) {
    var (selectedItem, setSelectedItem) = rememberSaveable { mutableStateOf("dashBoard") }

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AppPortrait(selectedItem, setSelectedItem,profileViewModel,navController)
        }
        WindowWidthSizeClass.Expanded -> {
            AppLandscape(selectedItem, setSelectedItem,profileViewModel,navController)
        }
    }
}