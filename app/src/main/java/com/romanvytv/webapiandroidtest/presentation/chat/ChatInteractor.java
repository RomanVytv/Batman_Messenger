package com.romanvytv.webapiandroidtest.presentation.chat;

import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.MessageViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

/**
 * Created by Роман on 21.08.2017.
 */

public interface ChatInteractor {

    void loadChatById(String token, int chatId, final ChatInteractor.OnLoadingFinishedListener listener);

    void loadChatByFriendId(String token, String friendId, final ChatInteractor.OnLoadingFinishedListener listener);

    void openChatById(String token, int chatId, final ChatInteractor.OnLoadingFinishedListener listener);

    void loadMembers(String token, int chatId, final ChatInteractor.OnLoadingFinishedListener listener);

    void loadCurrentUser(String token, final ChatInteractor.OnLoadingFinishedListener listener);

    void sendMessage(String authToken, int chatId, String message, final ChatInteractor.OnLoadingFinishedListener listener);

    interface OnLoadingFinishedListener {

        void onSuccessChat(ChatViewModel body);

        void onSuccessSend();

        void showError(String text);

        void onSuccessMessages(List<MessageViewModel> body);

        void onSuccessMembers(List<UserViewModel> body);

        void onSuccessUser(UserViewModel body);
    }
}
