package com.carlowil.audiolib.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.carlowil.audiolib.composables.BookItem
import com.carlowil.audiolib.models.Book
import com.carlowil.audiolib.sealed.DataState
import com.carlowil.audiolib.viewmodels.AudioLibViewModel

@Composable
fun LibraryScreen(
    audioLibViewModel: AudioLibViewModel,
    onBookSelected : (String) -> Unit
) {
    when(val result = audioLibViewModel.response.value) {
        is DataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is DataState.Success -> {
            ShowLazyList(result.data, onBookSelected)
        }

        is DataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize
                )
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error fetching data!",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize
                )
            }
        }

    }
}

@Composable
fun ShowLazyList(
    books: MutableList<Book>,
    onBookSelected : (String) -> Unit
){
    LazyColumn() {
        items(books){book ->
            BookItem(
                book = book,
                onBookSelected = onBookSelected
            )
        }
    }
}

