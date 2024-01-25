package com.example.petopia.view

import android.widget.Toast
import com.example.petopia.adapter.PetAdapter
import com.example.petopia.model.pojo.PetAdoption

interface IFragmentAdoptions {
    fun onError(message: String)
    fun onSuccess(petList: List<PetAdoption>)
}