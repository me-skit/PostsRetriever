package com.example.postretriever.api

import com.example.postretriever.model.Comment
import com.example.postretriever.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("comments")
    suspend fun getComments(
        @Query("postId") postId: Int
    ): Response<List<Comment>>
}