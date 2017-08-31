package com.romanvytv.webapiandroidtest.presentation.search;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

/**
 * Created by Роман on 28.08.2017.
 */

public interface ISearchView {

    void find(String token, String type, String value);

    void addFriend(String chatName, String userId);

    void fillUsers(List<UserViewModel> users);

    void showProgress();

    void hideProgress();

    void showError(String text);

    void navigateToMain();
}
