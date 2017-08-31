package com.romanvytv.webapiandroidtest.presentation.main;


public interface MainPresenter {

    /*void onBind(MainView view);

    void onUnbind();*/

    void fillUserData(String token);

    void loadChats(String token);
}
