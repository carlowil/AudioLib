package com.carlowil.audiolib.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlowil.audiolib.models.Book
import com.carlowil.audiolib.composables.BookItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier)
{
    val data = getData().toMutableStateList()
    Log.i("LibraryScreen", data.toString())
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = data) {
            BookItem(data = it)
        }
    }
}

fun getData() : List<Book> {
    val data = mutableListOf<Book>()
    val db = Firebase.firestore
    db.collection("BooksLib").get().addOnSuccessListener { query ->
        query.documents.forEach { document ->
            data.add(
                Book(
                    name = document.data?.get("name").toString(),
                    author = document.data?.get("author").toString(),
                    genre = document.data?.get("genre").toString(),
                    description = document.data?.get("description").toString(),
                    file = document.data?.get("file").toString(),
                    img = document.data?.get("img").toString()
                )
            )
            Log.i("FireBase", "${document.id} = ${document.data}")
        }
        if(data.size > 0) {
            Log.i("FireBase", "Data loaded!")
            Log.i("FireBase", "${data.size}")
        } else {
            Log.i("FireBase", "0 items data!")
        }
    }.addOnFailureListener {
        Log.w("FireBase", "Cant load FireStore")
    }
    Log.i("FireBase", data.toString())
    return data
}
