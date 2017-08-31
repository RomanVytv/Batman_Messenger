package com.romanvytv.webapiandroidtest.presentation.chat;

import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.data.api.ChatService;
import com.romanvytv.webapiandroidtest.data.api.UserService;
import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.MessageViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatInteractorImpl implements ChatInteractor {

    @Override
    public void loadChatById(String token, int chatId, final ChatInteractor.OnLoadingFinishedListener listener) {
        ChatService chatService = ApiServiceCreator.createService(ChatService.class,token);
        chatService.getChatById(chatId).enqueue(new Callback<ChatViewModel>() {
            @Override
            public void onResponse(Call<ChatViewModel> call, Response<ChatViewModel> response) {
                if (response.isSuccessful()){
                    listener.onSuccessChat(response.body());
                } else {
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ChatViewModel> call, Throwable t) {
                listener.showError(t.getMessage());
            }
        });
    }

    @Override
    public void loadChatByFriendId(String token, String friendId, final ChatInteractor.OnLoadingFinishedListener listener) {
        ChatService chatService = ApiServiceCreator.createService(ChatService.class,token);
        chatService.getChatByFriendId(friendId).enqueue(new Callback<ChatViewModel>() {
            @Override
            public void onResponse(Call<ChatViewModel> call, Response<ChatViewModel> response) {
                if (response.isSuccessful()){
                    listener.onSuccessChat(response.body());
                } else {
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ChatViewModel> call, Throwable t) {
                listener.showError(t.getMessage());
            }
        });
    }

    @Override
    public void openChatById(String token, int chatId, final OnLoadingFinishedListener listener) {
        ChatService chatService = ApiServiceCreator.createService(ChatService.class,token);
        chatService.openChat(chatId).enqueue(new Callback<List<MessageViewModel>>() {
            @Override
            public void onResponse(Call<List<MessageViewModel>> call, Response<List<MessageViewModel>> response) {
                if (response.isSuccessful()){
                    listener.onSuccessMessages(response.body());
                } else {
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<MessageViewModel>> call, Throwable t) {
                listener.showError(t.getMessage());
            }
        });
        /*listener.onSuccessMessages(Arrays.asList(new MessageViewModel[]{
            new MessageViewModel(1,"Hello world1","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:01"),
            new MessageViewModel(2,"Hello world2","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:02"),
            new MessageViewModel(3,"Hell","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:03"),
            new MessageViewModel(4,"Hello world4","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:04"),
            new MessageViewModel(5,"Hello world5","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:05"),
                new MessageViewModel(6,"Hello world1 Hello world1Hello world1Hello world1","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:01"),
                new MessageViewModel(8,"Hello world2","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:02"),
                new MessageViewModel(9,"Hello world3","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:03"),
                new MessageViewModel(10,"Hello world4","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:04"),
                new MessageViewModel(11,"Hello world5","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:05"),
                new MessageViewModel(1,"Hello world1","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:01"),
                new MessageViewModel(2,"Hello ","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:02"),
                new MessageViewModel(3,"Hello world3Hello world1Hello world1Hello world1Hello world1","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:03"),
                new MessageViewModel(4,"Hello world4","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:04"),
                new MessageViewModel(5,"Hello world5","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:05"),
                new MessageViewModel(6,"Hello worlHello world1Hello world1d1","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:01"),
                new MessageViewModel(8,"Hello world2","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:02"),
                new MessageViewModel(9,"Hello worldHello world1Hello world1Hello world13","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:03"),
                new MessageViewModel(10,"Hello world4","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:04"),
                new MessageViewModel(11,"Hello world5","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:05"),
                new MessageViewModel(1,"Hello world1","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:01"),
                new MessageViewModel(2,"Hello world2","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:02"),
                new MessageViewModel(3,"Hello world3","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:03"),
                new MessageViewModel(4,"Hello world4","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:04"),
                new MessageViewModel(5,"Hello world5","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:05"),
                new MessageViewModel(6,"Hello world1","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:01"),
                new MessageViewModel(8,"Hello world2","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:02"),
                new MessageViewModel(9,"Hello worHello world1vHello world1ld3","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:03"),
                new MessageViewModel(10,"Hello world4Hello world1Hello world1Hello world1","64be80c0-e4bf-4eb1-ac64-870abe4b576b",1,"12:04"),
                new MessageViewModel(11,"Hello world5","3ff371bd-8ad5-4082-859a-29aa829ccdcd",1,"12:05")}));*/
    }

    @Override
    public void loadMembers(String token, int chatId, final OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getChatMembersById(chatId).enqueue(new Callback<List<UserViewModel>>() {
            @Override
            public void onResponse(Call<List<UserViewModel>> call, Response<List<UserViewModel>> response) {
                if (response.isSuccessful()){
                    listener.onSuccessMembers(response.body());
                } else {
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserViewModel>> call, Throwable t) {
                listener.showError(t.getMessage());
            }
        });
    }

    @Override
    public void loadCurrentUser(String token, final OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getCurrentUser().enqueue(new Callback<UserViewModel>() {
            @Override
            public void onResponse(Call<UserViewModel> call, Response<UserViewModel> response) {
                if (response.isSuccessful()){
                    listener.onSuccessUser(response.body());
                } else {
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserViewModel> call, Throwable t) {
                listener.showError(t.getMessage());
            }
        });
    }

    @Override
    public void sendMessage(String authToken, int chatId, String message, final OnLoadingFinishedListener listener) {
        ChatService chatService = ApiServiceCreator.createService(ChatService.class,authToken);
        chatService.sendMessege(chatId,message).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    listener.onSuccessSend();
                } else {
                    listener.showError("Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.showError("Error: " + t.getMessage());
            }
        });
    }
}
