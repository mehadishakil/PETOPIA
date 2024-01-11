package com.example.petopia.view;

public interface ISignUp {
    void onSignupSuccess(String message, String otp, String user_id);
    void onSignupError(String message);
}
