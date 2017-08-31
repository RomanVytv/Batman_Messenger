package com.romanvytv.webapiandroidtest.presentation.main;


import android.util.Log;

import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.data.api.ChatService;
import com.romanvytv.webapiandroidtest.data.api.UserService;
import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements MainInteractor {


    @Override
    public void loadChats(String token,final MainInteractor.OnLoadingFinishedListener listener) {
        ChatService chatService = ApiServiceCreator.createService(ChatService.class,token);
        chatService.getChats().enqueue(new Callback<List<ChatViewModel>>() {
            @Override
            public void onResponse(Call<List<ChatViewModel>> call, Response<List<ChatViewModel>> response) {
                if(response.body() != null && response.isSuccessful()) {
                    listener.onSuccessChats(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ChatViewModel>> call, Throwable t) {
                listener.showError("Error - " + t.getMessage());
            }
        });
        /*listener.onSuccessChats( Arrays.asList(
                new ChatViewModel[]{
                        new ChatViewModel("131232", "Chat 11", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 22", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 33", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 44", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 55", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 66", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 77", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 88", "01.01.1234"),
                        new ChatViewModel("131232", "Chat 99", "01.01.1234")}));*/
    }

    @Override
    public void fillUserData(String token,final MainInteractor.OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class, token);
        userService.getCurrentUser().enqueue(new Callback<UserViewModel>() {
            @Override
            public void onResponse(Call<UserViewModel> call, Response<UserViewModel> response) {
                if(response.body() != null && response.isSuccessful()) {
                    listener.onSuccessUser(response.body());
                }

                if(!response.isSuccessful()){
                    //listener.showError(response.message());
                    Log.i(getClass().toString(),response.message());
                }
            }

            @Override
            public void onFailure(Call<UserViewModel> call, Throwable t) {
                    listener.showError("Error - " + t.getMessage());
            }
        });
    }

}
