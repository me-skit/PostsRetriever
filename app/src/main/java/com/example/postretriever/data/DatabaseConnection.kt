package com.example.postretriever.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.postretriever.model.Comment
import com.example.postretriever.model.Post

@Database(entities = [Post::class, Comment::class], version = 1, exportSchema = false)
abstract class DatabaseConnection : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseConnection? = null

        fun getDatabase(context: Context): DatabaseConnection {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseConnection::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}