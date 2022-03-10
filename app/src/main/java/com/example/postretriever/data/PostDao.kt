package com.example.postretriever.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.postretriever.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)
}