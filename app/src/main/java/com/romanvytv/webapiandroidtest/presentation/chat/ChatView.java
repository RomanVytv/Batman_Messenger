package com.romanvytv.webapiandroidtest.presentation.chat;

import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.MessageViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

/**
 * Created by Роман on 21.08.2017.
 */

public interface ChatView {

    void loadChat();

    void loadMessages();

    void loadCurrentUser();

    void loadMembers();

    void initRecyclerView();

    void showProgress();

    void hideProgress();

    void showError(String text);

    void initChat(ChatViewModel chat);

    void initMembers(List<UserViewModel> users);

    void initCurrentUser(UserViewModel user);

    void initMessages(List<MessageViewModel> messages);

    void sendMessage();
}

