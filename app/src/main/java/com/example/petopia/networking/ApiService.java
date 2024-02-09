package com.example.petopia.networking;


import com.example.petopia.model.pojo.Article;
import com.example.petopia.model.pojo.CompareOTP;
import com.example.petopia.model.pojo.OtpResponse;
import com.example.petopia.model.pojo.PetAdoption;
import com.example.petopia.model.pojo.Product;
import com.example.petopia.model.pojo.ServerResponse;
import com.example.petopia.model.pojo.User;
import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.UserID;
import com.example.petopia.model.pojo.YourPet;
import com.example.petopia.view.UploadArticle;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("OTP/verify_otp.php")
    Call<OtpResponse> registerUser(@Body User user);

    @POST("login.php")
    Call<ResponseUser> loginUser(@Body User user);

    @GET("events.php")
    Call<List<Event>> getEvents();

    @GET("get_articles.php")
    Call<List<Article>> getArticles();

    @GET("get_adoptions.php")
    Call<List<PetAdoption>> getAdoptPet();


    @POST("add_your_pet.php")
    Call<ServerResponse> addYourPet(@Body YourPet yourPet);

    @GET("get_your_pet.php")
    Call<List<YourPet>> getYourPets(@Query("user_id") String userID);

    @POST("signup.php")
    Call<ServerResponse> confirmAccount(@Body CompareOTP compareOTP);

    @POST("add_article.php")
    Call<ServerResponse> uploadArticle(@Body Article article);

    @POST("add_adoption.php")
    Call<ServerResponse> addAdoption(@Body PetAdoption petAdoption);

    @POST("add_products.php")
    Call<ServerResponse> addProduct(@Body Product product);

}