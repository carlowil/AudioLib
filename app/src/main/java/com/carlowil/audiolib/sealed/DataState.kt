package com.carlowil.audiolib.sealed

import com.carlowil.audiolib.models.Book

sealed class DataState {
    class Success(val data: MutableList<Book>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}