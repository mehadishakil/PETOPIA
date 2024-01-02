package com.example.petopia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petopia.R
import com.example.petopia.model.pojo.Services

class ServiceAdapter(private val services: List<Services>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceName: TextView = itemView.findViewById(R.id.serviceName)
        val serviceImage: ImageView = itemView.findViewById(R.id.serviceImage)
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
    }

    override fun getItemCount(): Int {
        return services.size
    }
}