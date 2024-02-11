package com.example.petopia.view

import com.example.petopia.model.pojo.Product

interface IFragmentShop {
    fun onGetTrendingProductSuccess(products: List<Product?>?)
    fun onGetTrendingProductError(message: String?)
}