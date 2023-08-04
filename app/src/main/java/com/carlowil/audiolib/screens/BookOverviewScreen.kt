package com.carlowil.audiolib.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.img)
                .crossfade(true)
                .build(),
            contentDescription = book.name,
            contentScale = ContentScale.Crop
        )
        Text(text = book.name)
        Text(text = book.author)
        Text(text = book.genre)
        Text(text = book.description)
    }
}