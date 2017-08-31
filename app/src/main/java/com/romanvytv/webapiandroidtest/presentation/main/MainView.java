package com.romanvytv.webapiandroidtest.presentation.main;


import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void fillCurrentUser(UserViewModel user);

    void fillChats(List<ChatViewModel> chats);

    void loadCurrentUser();

    void loadUsersChats(String token);

    void showError(String text);

}
