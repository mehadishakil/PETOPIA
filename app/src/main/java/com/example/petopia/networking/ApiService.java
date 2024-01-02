package com.example.petopia.networking;


import com.example.petopia.model.pojo.OtpResponse;
import com.example.petopia.model.pojo.ServerResponse;
import com.example.petopia.model.pojo.User;
import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.UserID;
import com.example.petopia.model.pojo.YourPet;
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

    @POST("add_your_pet.php")
    Call<ServerResponse> addYourPet(@Body YourPet yourPet);

    @GET("get_your_pet.php")
    Call<List<YourPet>> getYourPets(@Query("user_id") String userID);

    @POST("signup.php")
    Call<ResponseUser> confirmAccount(@Body UserID userID);

}