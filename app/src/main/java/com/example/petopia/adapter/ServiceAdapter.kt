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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.petopia.R
import com.example.petopia.model.pojo.Services
import com.example.petopia.view.MainActivity
import com.example.petopia.view.ViewProduct
import com.example.petopia.view.sServices


class ServiceAdapter(private val context: Context, private val services: List<Services>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceName: TextView = itemView.findViewById(R.id.serviceName)
        val serviceImage: ImageView = itemView.findViewById(R.id.serviceImage)
        val serviceLayout : ConstraintLayout = itemView.findViewById(R.id.servicelayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.services, parent, false)
        return ServiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val currentService = services[position]
        holder.serviceName.text = currentService.serviceName
        holder.serviceImage.setImageResource(currentService.serviceImage)


        holder.serviceLayout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, sServices::class.java)
            intent.putExtra("service_name", currentService.serviceName)
            startActivity(context, intent, null)
        })



    }

    override fun getItemCount(): Int {
        return services.size
    }
}