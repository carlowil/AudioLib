package com.carlowil.audiolib

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyBooksScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(text = "This is MyBooksScreen")
    }
}