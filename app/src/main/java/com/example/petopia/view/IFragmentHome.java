package com.example.petopia.view;

import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.YourPet;

import java.util.List;

public interface IFragmentHome {

    void onGetEvents(List<Event> eventList);

    void onErrorEvents(String message);

    void onGetYourPetError(String message);

    void onGetYourPetSuccess(List<YourPet> yourPetList);

}
