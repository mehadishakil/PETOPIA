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
import com.example.petopia.model.pojo.PetAdoption
import com.example.petopia.view.DisplayPetAdoption
import com.example.petopia.view.ReadArticle


class PetAdoptionAdapter(private val AdoptPet: List<PetAdoption>, val context: Context) :
    RecyclerView.Adapter<PetAdoptionAdapter.AdoptPetViewHolder>() {

    class AdoptPetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.petAdoptName)
        val category: TextView = itemView.findViewById(R.id.petAdoptCategory)
        val genderAge: TextView = itemView.findViewById(R.id.petAdoptGenderAge)
        val image: ImageView = itemView.findViewById(R.id.petAdopImg)
        val adoptionPetLayout : ConstraintLayout = itemView.findViewById(R.id.AdoptPetCardDesign)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetAdoptionAdapter.AdoptPetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.adopt_pet_card_design_layout, parent, false)
        return AdoptPetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetAdoptionAdapter.AdoptPetViewHolder, position: Int) {

        val presentPet = AdoptPet[position]
        holder.name.text = presentPet.name
        holder.category.text = presentPet.category
        holder.genderAge.text = presentPet.gender+", "+presentPet.age+" mo"
        val imageUrl =
            "https://petopia-pet.000webhostapp.com/PetAdoptionImage/" + presentPet.image
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.loading_image)
            .into(holder.image)


        holder.adoptionPetLayout.setOnClickListener(View.OnClickListener {
            try {
                val intent = Intent(context, DisplayPetAdoption::class.java)
                intent.putExtra("name", presentPet.name)
                intent.putExtra("category", presentPet.category)
                intent.putExtra("breed", presentPet.breed)
                intent.putExtra("gender", presentPet.gender)
                intent.putExtra("age", presentPet.age)
                intent.putExtra("weight", presentPet.weight)
                intent.putExtra("location", presentPet.location)
                intent.putExtra("description", presentPet.description)
                intent.putExtra("owner", presentPet.owner)
                intent.putExtra("contact", presentPet.contact)
                intent.putExtra("image", presentPet.image)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show()
            }
        })





    }

    override fun getItemCount(): Int {
        return AdoptPet.size
    }

}