package com.romanvytv.webapiandroidtest.presentation.friends;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

/**
 * Created by Роман on 24.08.2017.
 */

public interface FriendsView {

    void showProgress();

    void hideProgress();

    void showError(String text);

    void loadFriends(String token);

    void fillFriends(List<UserViewModel> friends);
}
