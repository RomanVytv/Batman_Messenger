package com.romanvytv.webapiandroidtest.presentation.login;



public interface LoginInteractor {

    interface OnLoginFinishedListener {

        void onEmailError();

        void onPasswordError();

        void onSuccess(String token);

        void showError(String text);
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
