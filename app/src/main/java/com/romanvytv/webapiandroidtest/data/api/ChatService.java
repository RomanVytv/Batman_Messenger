package com.romanvytv.webapiandroidtest.data.api;


import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.MessageViewModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**for creation of this service your should use authToken*/

public interface ChatService {

    @GET("api/chats")
    Call<List<ChatViewModel>> getChats();

    @GET("api/chats/{id}")
    Call<ChatViewModel> getChatById(@Path("id") int chatId);

    @POST("api/chats/{id}/send")
    Call<ResponseBody> sendMessege(@Path("id") int chatId, @Body String text);

    @POST("api/chats/create/{name}")
    Call<ResponseBody> createChat(@Path("name") String chatName, @Body String friendId);

    @POST("api/chats/{chatId}/member")
    Call<ResponseBody> addMember(@Path("chatId") int chatId, @Body String userId);

    @GET("api/chats/{chatId}/messages")
    Call<List<MessageViewModel>> openChat(@Path("chatId") int chatId);


}
