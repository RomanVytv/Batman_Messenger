package com.romanvytv.webapiandroidtest.presentation.user;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

/**
 * Created by Роман on 24.08.2017.
 */

public interface UserView {

    void loadUser(String authToken, String userId);

    void showProgress();

    void hideProgress();

    void showError(String text);

    void fillUser(UserViewModel user);

    void openChat();
}
