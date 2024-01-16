package com.example.petopia.view;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.petopia.adapter.PetAdapter;
import com.example.petopia.model.pojo.Article;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.YourPet;

import java.util.List;

public interface IFragmentHome {

    void onGetEvents(List<Event> eventList);

    void onErrorEvents(String message);

    void onGetYourPetError(String message);

    void onGetYourPetSuccess(List<YourPet> yourPetList);

    void onGetArticleError(String message);

    void OnGetArticleSuccess(List<Article> articles);

}
