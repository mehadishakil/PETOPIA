package com.example.petopia.model.repository;

import com.example.petopia.model.pojo.Article;
import com.example.petopia.model.pojo.CompareOTP;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.OtpResponse;
import com.example.petopia.model.pojo.PetAdoption;
import com.example.petopia.model.pojo.Product;
import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.pojo.ServerResponse;
import com.example.petopia.model.pojo.User;
import com.example.petopia.model.pojo.UserID;
import com.example.petopia.model.pojo.YourPet;
import com.example.petopia.networking.ApiService;
import com.example.petopia.networking.RetrofitClient;
import com.example.petopia.view.UploadArticle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Repository {
    private ApiService apiService;

    public Repository() {
        this.apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public void loginUser(User user, Callback<ResponseUser> callback) {
        Call<ResponseUser> call = apiService.loginUser(user);
        call.enqueue(callback);
    }

    public void registerUser(User user, Callback<OtpResponse> callback) {
        Call<OtpResponse> call = apiService.registerUser(user);
        call.enqueue(callback);
    }

    public void getEvents(Callback<List<Event>> callback) {
        Call<List<Event>> call = apiService.getEvents();
        call.enqueue(callback);
    }

    public void addYourPet(YourPet yourPet, Callback<ServerResponse> callback) {
        Call<ServerResponse> call = apiService.addYourPet(yourPet);
        call.enqueue(callback);
    }


    public void getYourPet(UserID userID, Callback<List<YourPet>> callback) {
        Call<List<YourPet>> call = apiService.getYourPets(userID.getUser_id());
        call.enqueue(callback);
    }

    public void verifyOTP(CompareOTP compareOTP, Callback<ServerResponse> callback) {
        Call<ServerResponse> call = apiService.confirmAccount(compareOTP);
        call.enqueue(callback);
    }


    public void addArticle(Article article, Callback<ServerResponse>callback){
        Call<ServerResponse> call = apiService.uploadArticle(article);
        call.enqueue(callback);
    }

    public void getArticles(Callback<List<Article>> callback) {
        Call<List<Article>> call = apiService.getArticles();
        call.enqueue(callback);
    }

    public void addAdoption(PetAdoption petAdoption, Callback<ServerResponse>callback){
        Call<ServerResponse> call = apiService.addAdoption(petAdoption);
        call.enqueue(callback);
    }

    public void getAdoptPet(Callback<List<PetAdoption>> callback) {
        Call<List<PetAdoption>> call = apiService.getAdoptPet();
        call.enqueue(callback);
    }

    public void addProduct(Product product, Callback<ServerResponse>callback){
        Call<ServerResponse> call = apiService.addProduct(product);
        call.enqueue(callback);
    }

    public void getProductsByCategory(String category, Callback<List<Product>> callback) {
        Call<List<Product>> call = apiService.getProductsByCategory(category);
        call.enqueue(callback);
    }



}
