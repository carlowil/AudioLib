package com.carlowil.audiolib.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.carlowil.audiolib.viewmodels.AudioLibViewModel

@Composable
fun BookOverviewScreen(
    bookId: String,
    viewModel: AudioLibViewModel
) {
    val book =  viewModel.getBook(bookId)!!
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = book.img,
            contentDescription = book.name
        )
        Text(text = book.name)
        Text(text = book.author)
        Text(text = book.genre)
        Text(text = book.description)
    }
}