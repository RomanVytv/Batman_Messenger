package com.romanvytv.webapiandroidtest.presentation.search;

import android.util.Log;

import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.data.api.ChatService;
import com.romanvytv.webapiandroidtest.data.api.UserService;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchInterceptorImpl implements SearchInterceptor {


    @Override
    public void findByEmail(String token, String query, final OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getUserByEmail(query).enqueue(new Callback<List<UserViewModel>>() {
            @Override
            public void onResponse(Call<List<UserViewModel>> call, Response<List<UserViewModel>> response) {
                if(response.isSuccessful()){
                    listener.onSuccessSearch(response.body());
                }else{
                    listener.showError(response.message());
                    Log.i("RvError",response.body() + "---" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserViewModel>> call, Throwable t) {
                listener.showError("Error - " + t.getMessage());
            }
        });
        /*listener.onSuccessSearch(Arrays.asList(new UserViewModel[]{
                new UserViewModel("id1","Name 1", "Surname1", "01.01.1234","+312122123","test1@gmail.com","05.02.2091" ),
                new UserViewModel("id2","Name 2", "Surname2", "01.01.1234","+312122123","test2@gmail.com","05.02.2091" ),
                new UserViewModel("id3","Name 3", "Surname3", "01.01.1234","+312122123","test3@gmail.com","05.02.2091" ),
                new UserViewModel("id4","Name 4", "Surname4", "01.01.1234","+312122123","test4@gmail.com","05.02.2091" ),
                new UserViewModel("id5","Name 5", "Surname5", "01.01.1234","+312122123","test5@gmail.com","05.02.2091" ),
                new UserViewModel("id6","Name 6", "Surname6", "01.01.1234","+312122123","test6@gmail.com","05.02.2091" ),
                new UserViewModel("id7","Name 7", "Surname7", "01.01.1234","+312122123","test7@gmail.com","05.02.2091" ),
                new UserViewModel("id8","Name 8", "Surname8", "01.01.1234","+312122123","test8@gmail.com","05.02.2091" ),
                new UserViewModel("id9","Name 9", "Surname9", "01.01.1234","+312122123","test9@gmail.com","05.02.2091" ),
                new UserViewModel("id11","Name 11", "Surname11", "01.01.1234","+312122123","test11@gmail.com","05.02.2091" ),
                new UserViewModel("id22","Name 22", "Surname22", "01.01.1234","+312122123","test22@gmail.com","05.02.2091" ),
                new UserViewModel("id33","Name 33", "Surname33", "01.01.1234","+312122123","test33@gmail.com","05.02.2091" )
        }));*/
    }

    @Override
    public void findByName(String token, String query,final  OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getUserByName(query).enqueue(new Callback<List<UserViewModel>>() {
            @Override
            public void onResponse(Call<List<UserViewModel>> call, Response<List<UserViewModel>> response) {
                if(response.isSuccessful()){
                    listener.onSuccessSearch(response.body());
                }else{
                    listener.showError(response.message());
                    Log.i("RvError",response.body() + "---" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserViewModel>> call, Throwable t) {
                listener.showError("Error - " + t.getMessage());
            }
        });
    }

    @Override
    public void findBySurname(String token, String query,final OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getUserBySurname(query).enqueue(new Callback<List<UserViewModel>>() {
            @Override
            public void onResponse(Call<List<UserViewModel>> call, Response<List<UserViewModel>> response) {
                if(response.isSuccessful()){
                    listener.onSuccessSearch(response.body());
                }else{
                    listener.showError(response.message());
                    Log.i("RvError",response.body() + "---" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserViewModel>> call, Throwable t) {
                listener.showError("Error - " + t.getMessage());
            }
        });
    }

    @Override
    public void findByPhone(String token, String query,final OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getUserByPhone(query).enqueue(new Callback<List<UserViewModel>>() {
            @Override
            public void onResponse(Call<List<UserViewModel>> call, Response<List<UserViewModel>> response) {
                if(response.isSuccessful()){
                    listener.onSuccessSearch(response.body());
                }else{
                    listener.showError(response.message());
                    Log.i("RvError",response.body() + "---" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserViewModel>> call, Throwable t) {
                listener.showError("Error - " + t.getMessage());
            }
        });
    }

    @Override
    public void addFriend(String token, String chatName, final String friendId, final OnLoadingFinishedListener listener) {
        ChatService chatService = ApiServiceCreator.createService(ChatService.class,token);
        chatService.createChat(chatName,friendId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    listener.onSuccessAddFriend();
                }else{
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.showError(t.getMessage());
            }
        });
    }
}
