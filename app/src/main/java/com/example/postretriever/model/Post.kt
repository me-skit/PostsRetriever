package com.example.postretriever.model

import androidx.room.Entity

@Entity(tableName = "post_table")
class Post(val userId: Int, val id: Int, val title: String, val body: String)