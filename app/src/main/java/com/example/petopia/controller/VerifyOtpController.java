package com.example.petopia.controller;

import android.util.Log;

import com.example.petopia.model.pojo.CompareOTP;
import com.example.petopia.model.pojo.OtpResponse;
import com.example.petopia.model.pojo.ServerResponse;
import com.example.petopia.model.pojo.User;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.ILogView;
import com.example.petopia.view.ISignView;
import com.example.petopia.view.IVerifiyOtpView;
import com.example.petopia.view.VerifyOtp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpController {

    IVerifiyOtpView iVerifiyOtpView;
    Repository repository;

    public VerifyOtpController(IVerifiyOtpView iVerifiyOtpView) {
        this.iVerifiyOtpView = iVerifiyOtpView;
        this.repository = new Repository();
    }

    public void onSignup(Boolean iVerified, String email, String unique_id) {
        CompareOTP compareOTP = new CompareOTP(iVerified, unique_id);


        repository.verifyOTP(compareOTP, new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null && "otp sent".equals(serverResponse.getMessage())) {
                        iVerifiyOtpView.OnSuccess(serverResponse.getMessage().toString());
                    } else {
                        iVerifiyOtpView.OnFailed("Otp does not matched");
                    }
                } else {
                    iVerifiyOtpView.OnFailed("HTTP Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                iVerifiyOtpView.OnFailed("Exception: " + t.getMessage());
                Log.d("tag", t.getMessage());
            }
        });

    }


}
