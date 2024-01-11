package com.example.petopia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petopia.R
import com.example.petopia.model.pojo.AdoptPet

class PetAdoptionCatgAdapter(private val adoptPet : List<AdoptPet>) :
    RecyclerView.Adapter<PetAdoptionCatgAdapter.AdoptPetCtgViewHolder>() {

    class AdoptPetCtgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petCatg: TextView = itemView.findViewById(R.id.categoryName)
        val petCatgImage: ImageView = itemView.findViewById(R.id.catetoryImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdoptPetCtgViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_fomat, parent, false)
        return PetAdoptionCatgAdapter.AdoptPetCtgViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return adoptPet.size
    }

    override fun onBindViewHolder(holder: AdoptPetCtgViewHolder, position: Int) {
        val pet = adoptPet[position]
        holder.petCatg.text = pet.petType
        holder.petCatgImage.setImageResource(pet.petTypeImage)
    }



}
