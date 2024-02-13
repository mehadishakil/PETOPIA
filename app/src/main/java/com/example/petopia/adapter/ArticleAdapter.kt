package com.example.petopia.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petopia.R
import com.example.petopia.model.pojo.Article
import com.example.petopia.view.ReadArticle


class ArticleAdapter(private val article: List<Article>, val context : Context) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.displayArticleTvTitle)
        val content: TextView = itemView.findViewById(R.id.displayArticleDetailsId)
        val image: ImageView = itemView.findViewById(R.id.displayIvArticle)
        val articleLayout : ConstraintLayout = itemView.findViewById(R.id.articleConstraintLayout)
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
        holder.title.text = currentArticle.title
        holder.content.text = currentArticle.content
        val imageUrl = "https://petopia-pet.000webhostapp.com/article_image/" + currentArticle.imageStore
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.loading_image)
            .into(holder.image)


        holder.articleLayout.setOnClickListener(View.OnClickListener {
            try {
                val intent = Intent(context, ReadArticle::class.java)
                intent.putExtra("title", currentArticle.title)
                intent.putExtra("content", currentArticle.content)
                intent.putExtra("imageData", currentArticle.imageStore)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun getItemCount(): Int {
        return article.size
    }

}