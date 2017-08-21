package com.romanvytv.webapiandroidtest.presentation.main;


import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.data.api.UserService;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements MainInteractor {

    @Override
    public void fillUserData(String token,final MainInteractor.OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class, token);
        userService.getCurrentUser().enqueue(new Callback<UserViewModel>() {
            @Override
            public void onResponse(Call<UserViewModel> call, Response<UserViewModel> response) {
                if(response.body() != null && response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }

                if(!response.isSuccessful()){
                    listener.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserViewModel> call, Throwable t) {
                    listener.showError("Error - " + t.getMessage());
            }
        });
    }

}
