package com.carlowil.audiolib.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.carlowil.audiolib.models.Book
import com.carlowil.audiolib.sealed.DataState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AudioLibViewModel : ViewModel() {

    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val tempList = mutableListOf<Book>()
        response.value = DataState.Loading
        val db = Firebase.firestore
        db.collection("BooksLib").get().addOnSuccessListener { query ->
            query.documents.forEach { document ->
                tempList.add(
                    Book(
                        name = document.data?.get("name").toString(),
                        author = document.data?.get("author").toString(),
                        genre = document.data?.get("genre").toString(),
                        description = document.data?.get("description").toString(),
                        file = document.data?.get("file").toString(),
                        img = document.data?.get("img").toString()
                    )
                )
            }
            response.value = DataState.Success(tempList)
        }.addOnFailureListener {
            response.value = DataState.Failure("Cant get data!")
        }
    }


}

