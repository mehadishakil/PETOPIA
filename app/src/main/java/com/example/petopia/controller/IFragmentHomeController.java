package com.example.petopia.controller;

import androidx.lifecycle.Observer;

import com.example.petopia.Observer.DataObserver;
import com.example.petopia.model.pojo.UserID;
import com.example.petopia.model.pojo.YourPet;

import java.util.List;

public interface IFragmentHomeController {
    void onGetEvents();
    void onGetYourPet(String userID);

    void addObserver(DataObserver observer);

    void removeObserver(DataObserver observer);

    void notifyObservers();

    void setData(List<YourPet> yourPets);
}
