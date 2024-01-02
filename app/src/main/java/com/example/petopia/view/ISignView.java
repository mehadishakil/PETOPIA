package com.example.petopia.view;

public interface ISignView {
    void onSignupSuccess(String message, String otp);
    void onSignupError(String message);
}
