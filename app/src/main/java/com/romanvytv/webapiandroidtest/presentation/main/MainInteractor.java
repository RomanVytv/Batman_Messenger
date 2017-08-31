package com.romanvytv.webapiandroidtest.presentation.main;


import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

public interface MainInteractor {



    interface OnLoadingFinishedListener {

        void onSuccessUser(UserViewModel user);

        void onSuccessChats(List<ChatViewModel> chats);

        void showError(String text);
    }

    void loadChats(String token, final MainInteractor.OnLoadingFinishedListener listener);

    void fillUserData(String token, final MainInteractor.OnLoadingFinishedListener listener);
}
