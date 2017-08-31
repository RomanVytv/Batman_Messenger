package com.romanvytv.webapiandroidtest.presentation.friends;

import android.util.Log;

import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.data.api.UserService;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Роман on 24.08.2017.
 */

public class FriendsInteractorImpl implements FriendsInteractor {

    @Override
    public void loadFriends(String token, final OnLoadingFinishedListener listener) {
        UserService userService = ApiServiceCreator.createService(UserService.class,token);
        userService.getFriends().enqueue(new Callback<List<UserViewModel>>() {
            @Override
            public void onResponse(Call<List<UserViewModel>> call, Response<List<UserViewModel>> response) {
                if(response.isSuccessful()){
                    listener.onSuccess(response.body());
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
}
