package com.romanvytv.webapiandroidtest.presentation.user;

import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.data.api.UserService;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class UserInteractorImpl implements UserInteractor {
    @Override
    public void loadUser(String token, String userId, final OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getUserById(userId).enqueue(new Callback<UserViewModel>() {
            @Override
            public void onResponse(Call<UserViewModel> call, Response<UserViewModel> response) {
                if(response.isSuccessful()){
                    listener.onSuccess(response.body());
                }else{
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserViewModel> call, Throwable t) {
                listener.showError(t.getMessage());
            }
        });
    }
}
