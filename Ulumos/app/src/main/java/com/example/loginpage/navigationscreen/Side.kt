package com.example.loginpage.navigationscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Side(selectedItem: String,onItemSelected: (String) -> Unit, modifier: Modifier = Modifier){
    var selectedItem by remember { mutableStateOf(selectedItem) }
    val a = NavigationRailItemColors(selectedIconColor= Color(0xFF3498DB),
        selectedTextColor= Color(0xFF3498DB),
        selectedIndicatorColor = Color.Transparent,
        unselectedIconColor= Color(0xFF999999),
        unselectedTextColor= Color(0xFF999999),
        disabledIconColor= Color(0xFF999999),
        disabledTextColor= Color(0xFF999999))

    androidx.compose.material3.NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                colors = a,
                icon = {
                    if (selectedItem == "dashBoard") {
                        Icon(
                            imageVector = Icons.Filled.Dashboard,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Dashboard,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text("DashBoard")
                },
                selected = selectedItem == "dashBoard",
                onClick = {
                    selectedItem = "dashBoard"
                    onItemSelected("dashBoard")
                }
            )
            NavigationRailItem(
                colors = a,
                icon = {
                    if (selectedItem == "assignments") {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.InsertDriveFile,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.InsertDriveFile,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text("Assignments")
                },
                selected = selectedItem == "assignments",
                onClick = {
                    selectedItem = "assignments"
                    onItemSelected("assignments")
                }
            )
            NavigationRailItem(
                colors = a,
                icon = {
                    if (selectedItem == "classVideos") {
                        Icon(
                            imageVector = Icons.Filled.PlayCircle,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.PlayCircle,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text("Class Videos")
                },
                selected = selectedItem == "classVideos",
                onClick = {
                    selectedItem = "classVideos"
                    onItemSelected("classVideos")
                }
            )
            NavigationRailItem(
                colors = a,
                icon = {
                    if (selectedItem == "profile") {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text("Profile")
                },
                selected = selectedItem == "profile",
                onClick = {
                    selectedItem = "profile"
                    onItemSelected(selectedItem)
                }
            )
        }
    }
}