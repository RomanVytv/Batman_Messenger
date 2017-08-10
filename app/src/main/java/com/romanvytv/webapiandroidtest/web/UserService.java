package com.romanvytv.webapiandroidtest.web;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

import retrofit2.Call;
import retrofit2.http.GET;



public interface UserService {

    @GET("api/users/current")
    Call<UserViewModel> getCurrentUser();

}
