package com.example.petopia.controller;

import android.content.Context;
import com.example.petopia.Observer.DataObserver;
import com.example.petopia.model.pojo.Article;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.UserID;
import com.example.petopia.model.pojo.YourPet;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.IFragmentHome;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHomeController implements IFragmentHomeController {


    private List<DataObserver> observers = new ArrayList<>();
    IFragmentHome fragmentHomeView;
    Context context;
    Repository repository;
    private List<Event> eventList = new ArrayList<>();
    private List<YourPet> petList = new ArrayList<>();
    private List<Article> articleList = new ArrayList<>();

    public FragmentHomeController(IFragmentHome FragmentHomeView, Context context) {
        this.fragmentHomeView = FragmentHomeView;
        this.context = context;
        this.repository = new Repository();
    }


    @Override
    public void onGetEvents() {

        try {
            repository.getEvents(new Callback<List<Event>>() {
                @Override
                public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        eventList = response.body();
                        fragmentHomeView.onGetEvents(eventList);
                    } else {
                        fragmentHomeView.onErrorEvents("Error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Event>> call, Throwable t) {
                    fragmentHomeView.onErrorEvents("Exception: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            fragmentHomeView.onErrorEvents("Exception: " + e.getMessage());
        }

    }


    @Override
    public void onGetYourPet(String user_id) {
        UserID userID = new UserID(user_id);
        try {
            repository.getYourPet(userID, new Callback<List<YourPet>>() {
                @Override
                public void onResponse(Call<List<YourPet>> call, Response<List<YourPet>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<YourPet> yourPetList = response.body();
                        fragmentHomeView.onGetYourPetSuccess(yourPetList);
                        setData(yourPetList);
                    } else {
                        fragmentHomeView.onGetYourPetError("Error 404");
                    }
                }

                @Override
                public void onFailure(Call<List<YourPet>> call, Throwable t) {
                    fragmentHomeView.onGetYourPetError("Exception: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            fragmentHomeView.onGetYourPetError("Exception: " + e.getMessage());
        }
    }



    @Override
    public void onGetArticle() {
        try {
            repository.getArticles(new Callback<List<Article>>() {
                @Override
                public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        articleList = response.body();
                        fragmentHomeView.OnGetArticleSuccess(articleList);
                    } else {
                        fragmentHomeView.onGetArticleError("Error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Article>> call, Throwable t) {
                    fragmentHomeView.onGetArticleError("Exception: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            fragmentHomeView.onGetArticleError("Exception: " + e.getMessage());
        }
    }



    public void setData(List<YourPet> yourPets) {
        this.petList = yourPets;
        notifyObservers();
    }



    @Override
    public void addObserver(DataObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(DataObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (DataObserver observer : observers) {
            observer.onDataChanged(petList);
        }
    }





}

