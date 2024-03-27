package com.example.loginpage.navigationscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LogoutDialog(onDismiss: () -> Unit, onLogout: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Log out of your account?")},
        dismissButton = {
            Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth().border(1.dp,Color(0xFF3498DB), CircleShape), colors = ButtonColors(containerColor = Color(0xFFFFFFFF), contentColor = Color(0xFF3498DB), disabledContainerColor = Color.White, disabledContentColor = Color.White)) {
                Text(text = "Cancel", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        },
        confirmButton = {
            Button(onClick = {
                onDismiss()
                onLogout()
            }, modifier = Modifier.fillMaxWidth().clip(CircleShape), colors = ButtonColors(containerColor = Color(0xFF3498DB), contentColor = Color.White, disabledContainerColor = Color.White, disabledContentColor = Color.White)) {
                Text(text = "Log out", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        },
        modifier = Modifier.wrapContentWidth().background(Color.White),
        containerColor = Color.White,
    )
}