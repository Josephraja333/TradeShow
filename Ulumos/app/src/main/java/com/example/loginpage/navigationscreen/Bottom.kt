package com.example.loginpage.navigationscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun Bottom(selectedItem: String, onItemSelected: (String) -> Unit, modifier: Modifier = Modifier){
    var selectedItem by remember { mutableStateOf(selectedItem) }
    val a = NavigationBarItemColors(selectedIconColor= Color(0xFF3498DB),
        selectedTextColor= Color(0xFF3498DB),
        selectedIndicatorColor = Color.Transparent,
        unselectedIconColor= Color(0xFF999999),
        unselectedTextColor= Color(0xFF999999),
        disabledIconColor= Color(0xFF999999),
        disabledTextColor= Color(0xFF999999))

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier,
    ) {

        NavigationBarItem(
            colors = a ,
            icon = {
                if (selectedItem == "dashBoard") {
                    Icon(
                        imageVector = Icons.Filled.Dashboard,
                        contentDescription = null
                    )
                }
                else{
                    Icon(
                        imageVector = Icons.Outlined.Dashboard,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text("DashBoard",textAlign = TextAlign.Center)
            },
            selected = selectedItem == "dashBoard",
            onClick = {
                selectedItem = "dashBoard"
                onItemSelected(selectedItem)
            }
        )

        NavigationBarItem(
            colors = a ,
            icon = {
                if (selectedItem == "assignments") {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.InsertDriveFile,
                        contentDescription = null
                    )
                }
                else{
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.InsertDriveFile,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text("Assignments",textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis )
            },
            selected = selectedItem == "assignments",
            onClick = { selectedItem = "assignments"
                onItemSelected(selectedItem)}
        )

        NavigationBarItem(
            colors = a ,
            icon = {
                if (selectedItem == "classVideos") {
                    Icon(
                        imageVector = Icons.Filled.PlayCircle,
                        contentDescription = null
                    )
                }
                else{
                    Icon(
                        imageVector = Icons.Outlined.PlayCircle,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text("Class Videos",textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis )
            },
            selected = selectedItem == "classVideos",
            onClick = {selectedItem = "classVideos"
                onItemSelected(selectedItem)}
        )
        NavigationBarItem(
            colors = a,
            icon = {
                if (selectedItem == "profile") {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null
                    )
                }
                else{
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text("Profile",textAlign = TextAlign.Center)
            },
            selected = selectedItem == "profile",
            onClick = {selectedItem = "profile"
                onItemSelected(selectedItem)}
        )
    }
}