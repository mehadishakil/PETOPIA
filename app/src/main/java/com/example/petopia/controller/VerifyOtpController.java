package com.example.petopia.controller;

import android.util.Log;

import com.example.petopia.model.pojo.CompareOTP;
import com.example.petopia.model.pojo.ServerResponse;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.IVerifiyOtp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpController {

    IVerifiyOtp iVerifiyOtp;
    Repository repository;

    public VerifyOtpController(IVerifiyOtp iVerifiyOtp) {
        this.iVerifiyOtp = iVerifiyOtp;
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
                        iVerifiyOtp.OnSuccess(serverResponse.getMessage().toString());
                    } else {
                        iVerifiyOtp.OnFailed("Otp does not matched");
                    }
                } else {
                    iVerifiyOtp.OnFailed("HTTP Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                iVerifiyOtp.OnFailed("Exception: " + t.getMessage());
                Log.d("tag", t.getMessage());
            }
        });

    }


}
