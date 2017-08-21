package com.romanvytv.webapiandroidtest.data.api;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**for creation of this service your should use authToken*/

public interface UserService {

    @GET("api/users/current")
    Call<UserViewModel> getCurrentUser();

    @GET("api/users")
    Call<List<UserViewModel>> getAllUsers();

    @GET("api/friends")
    Call<List<UserViewModel>> getFriends();

    @GET("api/users/{id}")
    Call<UserViewModel> getUserById(@Path("id") String id);

    @GET("api/users/name/{firstName}")
    Call<UserViewModel> getUserByName(@Path("firstName") String firstName);

    @GET("api/users/surname/{surname}")
    Call<UserViewModel> getUserBySurname(@Path("surname") String surname);

    @GET("api/users/phone/{phoneNumber}")
    Call<UserViewModel> getUserByPhoneNumber(@Path("phoneNumber") String phoneNumber);

    @POST("api/friends/{userId}")
    Call<ResponseBody> addFriend(@Path("userId") String id);

    @DELETE("api/friends/{userId}")
    Call<ResponseBody> deleteFriend(@Path("userId") String id);

    @POST("api/Account/Logout")
    Call<ResponseBody> logout();

}
