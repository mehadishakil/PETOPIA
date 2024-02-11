package com.example.petopia.controller

import com.example.petopia.model.pojo.PetAdoption
import com.example.petopia.model.pojo.Product
import com.example.petopia.model.pojo.ServerResponse
import com.example.petopia.model.repository.Repository
import com.example.petopia.view.AddProducts
import retrofit2.Call
import retrofit2.Response

class AddProductController(addProducts: AddProducts) {
    private val repository = Repository()
    val addProducts = addProducts

    fun onAddProduct(category:String, title: String, description: String, price: String, image: String, ratings: String) {
        val product = Product(null , title, description, price, ratings, image, category)

        repository.addProduct(product, object : retrofit2.Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                response: Response<ServerResponse?>
            ) {
                if (response.isSuccessful) {
                    val serverResponse = response.body()
                    if (serverResponse != null && "Successful" == serverResponse.message) {
                        addProducts.onSuccess(serverResponse.message)
                    } else {
                        addProducts.onError("Error on Uploading Product" + serverResponse!!.message)
                    }
                } else {
                    addProducts.onError("HTTP Error: " + response.code())
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                addProducts.onError("Exception: " + t.message)
            }
        })

    }
}