package com.example.postretriever.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.postretriever.R
import com.example.postretriever.ShowCommentsActivity
import com.example.postretriever.model.Post
import com.example.postretriever.utils.Constants

class PostAdapter: RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var list = emptyList<Post>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvUser).text = list[position].userId.toString()
        holder.itemView.findViewById<TextView>(R.id.tvBody).text = list[position].body

        val title =  "Post ${list[position].id}: ${list[position].title}"
        val titleView = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        titleView.text = title
        titleView.setOnClickListener {
            val intent = Intent(titleView.context, ShowCommentsActivity::class.java).apply {
                putExtra(Constants.EXTRA_POST_TITLE, title)
                putExtra(Constants.EXTRA_POST_ID, list[position].id.toString())
            }
            startActivity(titleView.context, intent, intent.extras)
        }
    }

    override fun getItemCount() = list.size

    fun setData(newList: List<Post>) {
        list = newList
        notifyDataSetChanged()
    }
}