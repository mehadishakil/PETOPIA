package com.example.petopia.controller;


import android.util.Log;

import com.example.petopia.model.pojo.OtpResponse;
import com.example.petopia.model.pojo.User;
import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.ILogView;
import com.example.petopia.view.ISignView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginController implements ILogController {

    ILogView loginView;
    ISignView signupView;
    Repository repository;

    public LoginController(ILogView loginView) {
        this.loginView = loginView;
        this.repository = new Repository();
    }

    public LoginController(ISignView signupView) {
        this.signupView = signupView;
        this.repository = new Repository();
    }

    @Override
    public void onLogin(String email, String password) {
        User user = new User(email, password);

        repository.loginUser(user, new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()) {
                    ResponseUser responseUser = response.body();
                    if (responseUser != null && "Successful".equals(responseUser.getMessage())) {
                        loginView.onLoginSuccess(responseUser.getMessage(), responseUser.getUser_id());
                    } else {
                        loginView.onLoginError("Incorrect username or password" + responseUser.getMessage());
                    }
                } else {
                    loginView.onLoginError("HTTP Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                loginView.onLoginError("Exception: " + t.getMessage());
            }
        });
    }

    @Override
    public void onSignup(String email, String pass1, String pass2) {
        User user = new User(email, pass1);
        int signupCode = user.isValid();

        if (signupCode == 0) {
            signupView.onSignupError("Please Enter Email");
        } else if (signupCode == 1) {
            signupView.onSignupError("Please enter a valid email");
        } else if (signupCode == 2) {
            signupView.onSignupError("Please enter a password");
        } else if (signupCode == 3) {
            signupView.onSignupError("Password should be more than 7 characters");
        } else if (!pass1.equals(pass2)) {
            signupView.onSignupError("Password must be same " + pass1 + " " + pass2);
        } else {
            repository.registerUser(user, new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    if (response.isSuccessful()) {
                        OtpResponse otpResponse = response.body();
                        if (otpResponse != null && "otp sent".equals(otpResponse.getMessage())) {
                            signupView.onSignupSuccess(otpResponse.getMessage(), otpResponse.getOtp(), otpResponse.getUnique_id());
                        } else {
                            signupView.onSignupError("Registration failed");
                        }
                    } else {
                        signupView.onSignupError("HTTP Error: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    signupView.onSignupError("Exception: " + t.getMessage());
                    Log.d("tag", t.getMessage());
                }
            });
        }
    }
}
