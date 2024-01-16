package com.example.petopia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petopia.R
import com.example.petopia.model.pojo.Article

class ArticleAdapter(private val article: List<Article>, val context : Context) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.displayArticleTvTitle)
        val content: TextView = itemView.findViewById(R.id.displayArticleTvDate)
        val image: ImageView = itemView.findViewById(R.id.displayIvArticle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.display_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {

        val currentArticle = article[position]
        holder.title.text = currentArticle.imageStore
        holder.content.text = currentArticle.content
        val imageUrl = "https://petopia-pet.000webhostapp.com/article_image/" + currentArticle.imageStore
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.loading_image)
            .error(R.drawable.error_image)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return article.size
    }

}