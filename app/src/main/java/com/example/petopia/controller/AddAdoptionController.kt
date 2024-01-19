package com.example.petopia.controller

import com.example.petopia.model.pojo.Article
import com.example.petopia.model.pojo.PetAdoption
import com.example.petopia.model.pojo.ServerResponse
import com.example.petopia.model.repository.Repository
import com.example.petopia.view.AddAdoption
import retrofit2.Call
import retrofit2.Response

class AddAdoptionController(addAdoption: AddAdoption) {
    private val repository = Repository()
    val addAdoption = addAdoption

    fun onAddAdoption(name: String, category: String, breed: String, gender: String, age: String, weight: String, location: String, description: String, owner: String, contact: String, image: String) {
        val petAdoption = PetAdoption(name, category, breed, gender, age, weight, location, description, owner, contact, image)

        repository.addAdoption(petAdoption, object : retrofit2.Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                response: Response<ServerResponse?>
            ) {
                if (response.isSuccessful) {
                    val serverResponse = response.body()
                    if (serverResponse != null && "Successful" == serverResponse.message) {
                        addAdoption.onSuccess(serverResponse.message)
                    } else {
                        addAdoption.onError("Error on Adding Pet for Adoption" + serverResponse!!.message)
                    }
                } else {
                    addAdoption.onError("HTTP Error: " + response.code())
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                addAdoption.onError("Exception: " + t.message)
            }
        })

    }
}