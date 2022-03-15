package com.example.postretriever.repository

import com.example.postretriever.api.RetrofitInstance
import com.example.postretriever.model.Comment
import com.example.postretriever.model.Post
import retrofit2.Response

class RetrofitRepository {
    suspend fun getPosts(): Response<List<Post>> {
        return RetrofitInstance.api.getPosts()
    }

    suspend fun getComments(userId: Int): Response<List<Comment>> {
        return RetrofitInstance.api.getComments(userId)
    }
}