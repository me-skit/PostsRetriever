package com.example.postretriever.model

import androidx.room.Entity

@Entity(tableName = "comment_table")
class Comment(val postId: Int, val id: Int, val name: String, val email: String, val body: String)