package com.example.petopia.controller

import android.content.Context
import com.example.petopia.model.pojo.Event
import com.example.petopia.model.pojo.PetAdoption
import com.example.petopia.model.repository.Repository
import com.example.petopia.view.FragmentAdoptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAdoptionController(
    private val fragmentAdoptionsInstance: FragmentAdoptions,
    context: Context
) : IFragmentAdoptionController {

    private val repository = Repository()
    private var adoptPetList: List<PetAdoption> = mutableListOf()

    override fun onGetPetAdoption() {
        repository.getAdoptPet(object : Callback<List<PetAdoption>> {
            override fun onResponse(
                call: Call<List<PetAdoption>>,
                response: Response<List<PetAdoption>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    adoptPetList = response.body()!!
                    fragmentAdoptionsInstance.onSuccess(adoptPetList)
                } else {
                    fragmentAdoptionsInstance.onError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<PetAdoption>>, t: Throwable) {
                fragmentAdoptionsInstance.onError("Exception: ${t.message}")
            }
        })
    }
}
