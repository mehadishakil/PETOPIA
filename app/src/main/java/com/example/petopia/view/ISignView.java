package com.example.petopia.view;

public interface ISignView {
    void onSignupSuccess(String message, String otp, String user_id);
    void onSignupError(String message);
}
