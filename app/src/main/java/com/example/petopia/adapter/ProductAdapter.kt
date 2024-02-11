package com.example.petopia.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petopia.R
import com.example.petopia.model.pojo.Product
import com.example.petopia.view.ReadArticle
import com.example.petopia.view.ViewProduct

class ProductAdapter(private val products: List<Product>, val context: Context) :
        RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitle: TextView = itemView.findViewById(R.id.IdItemName)
        val productPrice: TextView = itemView.findViewById(R.id.IdItemPrice)
        val productImage: ImageView = itemView.findViewById(R.id.ProductItemImage)
        val productLayout: View = itemView.findViewById(R.id.ProductDesignCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_design_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val currentProduct = products[position]
        holder.productTitle.text = currentProduct.title
        holder.productPrice.text = currentProduct.price + " tk"
        val imageUrl = "https://petopia-pet.000webhostapp.com/product_images/" + currentProduct.image
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.error_image)
                .into(holder.productImage)


        holder.productLayout.setOnClickListener(View.OnClickListener {
            try {
                val intent = Intent(context, ViewProduct::class.java)
                intent.putExtra("object", currentProduct)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun getItemCount(): Int {
        return products.size
    }

}