package com.example.petopia.Observer;

import com.example.petopia.model.pojo.YourPet;

import java.util.List;

public interface DataObserver {
    void onDataChanged(List<YourPet> yourPetList);
}