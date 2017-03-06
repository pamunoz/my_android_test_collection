package com.example.android.datafrominternet.data.remote;

import com.example.android.datafrominternet.data.model.GithubResult;
import com.example.android.datafrominternet.data.model.Item;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitApiInterface {

    @GET("/search/users")
    Call<GithubResult> getUsersByName(@Query("q") String name);

    @POST("/user/create")
    Call<Item> createUser(@Body String name, @Body String email);

    @PUT("/user/{id}/update")
    Call<Item> updateUser(@Path("id") String id , @Body Item user);
}