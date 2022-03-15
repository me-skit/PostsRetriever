package com.example.postretriever.repository

import com.example.postretriever.data.CommentDao
import com.example.postretriever.data.PostDao
import com.example.postretriever.model.Comment
import com.example.postretriever.model.Post

class DatabaseRepository(private val postDao: PostDao, private val commentDao: CommentDao) {
    suspend fun addPost(post: Post) {
        postDao.addPost(post)
    }

    suspend fun updatePost(post: Post) {
        postDao.updatePost(post)
    }

    suspend fun addComment(comment: Comment) {
        commentDao.addComment(comment)
    }

    suspend fun updateComment(comment: Comment) {
        commentDao.updateComment(comment)
    }
}