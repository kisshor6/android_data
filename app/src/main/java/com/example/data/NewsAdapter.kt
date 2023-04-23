package com.example.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val context : Context, private val allNews : List<News>, private val listener: itemClicked) : RecyclerView.Adapter<NewsAdapter.viewHolder>() {
    class viewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){

        val Headline = itemview.findViewById<TextView>(R.id.contentHead)
        val Description = itemview.findViewById<TextView>(R.id.contentDesc)
        val Category = itemview.findViewById<TextView>(R.id.contentCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.headline, parent, false)
        val ViewHolder = viewHolder(view)
        view.setOnClickListener {
            listener.onItemClickOfNews(allNews[ViewHolder.adapterPosition])
        }

        return ViewHolder
    }

    override fun getItemCount(): Int {
        return allNews.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentPosition = allNews[position]
        holder.Headline.text = currentPosition.headline
        holder.Description.text = currentPosition.description
        holder.Category.text = currentPosition.category

    }
}
interface itemClicked{
    fun onItemClickOfNews(news: News)
}