package com.example.postretriever

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postretriever.adapter.CommentAdapter
import com.example.postretriever.repository.RetrofitRepository
import com.example.postretriever.utils.Constants
import com.example.postretriever.viewmodel.RetrofitViewModel
import com.example.postretriever.viewmodel.RetrofitViewModelFactory

class ShowCommentsActivity : AppCompatActivity() {

    private lateinit var viewModel: RetrofitViewModel
    private val commentAdapter by lazy { CommentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_comments)

        val title = intent.getStringExtra(Constants.EXTRA_POST_TITLE)
        val id = intent.getStringExtra(Constants.EXTRA_POST_ID)
        findViewById<TextView>(R.id.tvPostTitle).apply {
            text = title
        }

        setupRecyclerView()

        val repository = RetrofitRepository()
        val viewModelFactory = RetrofitViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RetrofitViewModel::class.java)
        id?.let { viewModel.getComments(it.toInt()) }
        viewModel.comments.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let { commentAdapter.setData(it) }
            }
            else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        val commentRecyclerView = findViewById<RecyclerView>(R.id.rvComments)
        commentRecyclerView.adapter = commentAdapter
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}