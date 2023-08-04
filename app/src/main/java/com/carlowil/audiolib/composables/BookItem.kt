package com.carlowil.audiolib.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.carlowil.audiolib.models.Book
import com.carlowil.audiolib.navigation.BookOverview

@Composable
fun BookItem(
    modifier : Modifier = Modifier,
    book : Book,
    onBookSelected : (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onBookSelected("${BookOverview.route}/${book.id}") },
        shape = CardDefaults.elevatedShape,
    ) {
        CardContent(book = book)
    }
}

@Composable
fun CardContent(
    book : Book
) {
    Row(
        modifier = Modifier.padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.img)
                .crossfade(true)
                .build(),
            contentDescription = "Book image"
        )
        Column(
            modifier = Modifier
                .padding(6.dp)
                .weight(1f)
        ) {
            Text(book.name)
            Text(book.author)
            Text(book.genre)
        }
    }
}

@Preview
@Composable
fun BookItemPreview() {
    Surface() {
        
    }
}