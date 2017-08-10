package com.romanvytv.webapiandroidtest.web;

import com.romanvytv.webapiandroidtest.models.RegisterModel;
import com.romanvytv.webapiandroidtest.models.TokenModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Роман on 09.08.2017.
 */

public interface AccountService {

    @FormUrlEncoded
    @POST("Token")
    Call<TokenModel> getToken(@Field("grant_type") String grant_type,
                              @Field("username") String username,
                              @Field("password") String password );


    @POST("api/Account/Register")
    Call<ResponseBody> register(@Body RegisterModel model);
}
