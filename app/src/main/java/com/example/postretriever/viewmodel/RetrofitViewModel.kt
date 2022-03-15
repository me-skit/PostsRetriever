package com.example.postretriever.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postretriever.model.Comment
import com.example.postretriever.model.Post
import com.example.postretriever.repository.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RetrofitViewModel(private val repository: RetrofitRepository): ViewModel() {
    val posts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val comments: MutableLiveData<Response<List<Comment>>> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch {
            val response = repository.getPosts()
            posts.value = response
        }
    }

    fun getComments(postId: Int) {
        viewModelScope.launch {
            val response = repository.getComments(postId)
            comments.value = response
        }
    }
}