package com.example.postretriever.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postretriever.R
import com.example.postretriever.model.Comment

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private var list = emptyList<Comment>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvCommentId).text = list[position].id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvName).text = list[position].name
        holder.itemView.findViewById<TextView>(R.id.tvMail).text = list[position].email
        holder.itemView.findViewById<TextView>(R.id.tvBody).text = list[position].body
    }

    override fun getItemCount() = list.size

    fun setData(newList: List<Comment>) {
        list = newList
        notifyDataSetChanged()
    }
}