package com.example.petopia.model.pojo
import java.io.Serializable


data class Product(
        val id : String?,
        val title: String,
        val description: String,
        val price: String,
        val ratings: String,
        val image: String,
        val category: String,
): Serializable
