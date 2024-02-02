package com.example.petopia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petopia.R
import com.example.petopia.model.pojo.AdoptPet
import com.example.petopia.model.pojo.ShopCategory

class ShopCategoryAdapter(private val shopCatg : List<ShopCategory>) :
    RecyclerView.Adapter<ShopCategoryAdapter.ShopCatgViewHolder>() {

    class ShopCatgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shopCatg: TextView = itemView.findViewById(R.id.shopCategory)
        val shopCatgImage: ImageView = itemView.findViewById(R.id.shopCatgImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopCatgViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_category_format, parent, false)
        return ShopCategoryAdapter.ShopCatgViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return shopCatg.size
    }

    override fun onBindViewHolder(holder: ShopCatgViewHolder, position: Int) {
        val catg = shopCatg[position]
        holder.shopCatg.text = catg.shopCategory
        holder.shopCatgImage.setImageResource(catg.shopImage)
    }



}
