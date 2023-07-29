package com.carlowil.audiolib

import android.app.Activity
import android.content.Context
import android.os.Message
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.storage.FirebaseStorage

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    val list = mutableListOf<String>()
    val storage = FirebaseStorage.getInstance("gs://audiolib-2b8b9.appspot.com")
    val storageRef = storage.reference.child("Books/").listAll().addOnSuccessListener { 
        for (item in it.items) {
            list.add(item.path)
        }
    }.addOnFailureListener {

    }

    Surface() {
        Column() {
            list.forEach { 

            }
        }
    }
}

