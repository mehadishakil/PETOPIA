package com.example.petopia.controller

import android.content.Context
import android.widget.Toast
import com.example.petopia.model.pojo.Product
import com.example.petopia.model.repository.Repository
import com.example.petopia.view.FragmentShop
import retrofit2.Call
import retrofit2.Response

class FragmentShopController(
    private val fragmentShopInstance : FragmentShop,
    context: Context
) : IFragmentShopController {

    private val context : Context = context
    private val repository = Repository()
    private var trendingProductList : List<Product> = mutableListOf()


    override fun onGetPopularProducts() {
        repository.getProductsByCategory("Food", object : retrofit2.Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    trendingProductList = response.body()!!
                    fragmentShopInstance.onGetTrendingProductSuccess(trendingProductList)
                } else {
                    fragmentShopInstance.onGetTrendingProductError("Error: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                fragmentShopInstance.onGetTrendingProductError("Exception: ${t.message}")
            }
        })
    }



}