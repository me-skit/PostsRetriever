package com.example.postretriever.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.postretriever.model.Comment

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComment(comment: Comment)

    @Update
    suspend fun updateComment(comment: Comment)
}