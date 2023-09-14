package com.carlowil.audiolib.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    
    var login by rememberSaveable {
        mutableStateOf("")
    }
    
    var password by rememberSaveable {
        mutableStateOf("")
    }
    
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Login") },
            placeholder = { Text("example@gmail.com") }
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Example123!") }
        )

        Button(
            onClick = {}
        ) {
            Text("Login")
        }

    }
}

@Preview()
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
