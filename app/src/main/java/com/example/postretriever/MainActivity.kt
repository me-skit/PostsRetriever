package com.example.postretriever

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postretriever.adapter.PostAdapter
import com.example.postretriever.model.Post
import com.example.postretriever.repository.RetrofitRepository
import com.example.postretriever.viewmodel.DatabaseViewModel
import com.example.postretriever.viewmodel.RetrofitViewModel
import com.example.postretriever.viewmodel.RetrofitViewModelFactory

class MainActivity : AppCompatActivity() {
    // networking
    private lateinit var viewModel: RetrofitViewModel
    private val postAdapter by lazy { PostAdapter() }
    // database
    private lateinit var databaseViewModel: DatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //databaseViewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)

        setupRecyclerView()

        val repository = RetrofitRepository()
        val viewModelFactory = RetrofitViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RetrofitViewModel::class.java)
        viewModel.getPosts()
        viewModel.posts.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let { postAdapter.setData(it) }
                //response.body()?.forEach {
                    //databaseViewModel.addPost(it)
                //}
            }
            else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        val postRecyclerView = findViewById<RecyclerView>(R.id.rvPosts)
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}