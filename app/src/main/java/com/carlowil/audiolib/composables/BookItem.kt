package com.carlowil.audiolib.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.carlowil.audiolib.models.Book

@Composable
fun BookItem(
    modifier : Modifier = Modifier,
    data : Book
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp),
        shape = CardDefaults.elevatedShape,
    ) {
        CardContent(data = data)
    }
}

@Composable
fun CardContent(
    data : Book
) {
    Row(
        modifier = Modifier.padding(6.dp)
    ) {
        AsyncImage(
            model = data.img,
            contentDescription = "Book image"
        )
        Column(
            modifier = Modifier
                .padding(6.dp)
                .weight(1f)
        ) {
            Text(data.name)
            Text(data.author)
        }
    }
}

@Preview
@Composable
fun BookItemPreview() {
    Surface() {
        
    }
}