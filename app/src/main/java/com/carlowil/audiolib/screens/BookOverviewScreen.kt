package com.carlowil.audiolib.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.carlowil.audiolib.viewmodels.AudioLibViewModel

@Composable
fun BookOverviewScreen(
    bookId: String,
    viewModel: AudioLibViewModel
) {
    val book =  viewModel.getBook(bookId)!!
    val uri = Uri.parse(book.file)
    Box(contentAlignment = Alignment.BottomEnd) {
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
        Button(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),
            onClick = {}
        ) {
            Text(text = "Читать!")
        }
    }
}
