package com.romanvytv.webapiandroidtest.presentation.main;


import com.romanvytv.webapiandroidtest.models.UserViewModel;

public interface MainView {

    void showProgress();

    void hideProgress();

    void fillCurrentUser(UserViewModel user);

    void loadCurrentUser();

    void loadUsersChats();

    void showError(String text);

}
