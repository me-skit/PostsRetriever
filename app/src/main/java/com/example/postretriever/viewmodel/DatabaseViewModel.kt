package com.example.postretriever.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.postretriever.data.DatabaseConnection
import com.example.postretriever.model.Comment
import com.example.postretriever.model.Post
import com.example.postretriever.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application): AndroidViewModel(application) {
    private val repository : DatabaseRepository

    init {
        val postDao = DatabaseConnection.getDatabase(application).postDao()
        val commentDao = DatabaseConnection.getDatabase(application).commentDao()

        repository = DatabaseRepository(postDao, commentDao)
    }

    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPost(post)
        }
    }

    fun updatePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePost(post)
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addComment(comment)
        }
    }

    fun updateComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateComment(comment)
        }
    }
}