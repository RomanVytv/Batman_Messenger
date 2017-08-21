package com.romanvytv.webapiandroidtest.presentation.login;

/**
 * Created by Роман on 15.08.2017.
 */

public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();

    }
