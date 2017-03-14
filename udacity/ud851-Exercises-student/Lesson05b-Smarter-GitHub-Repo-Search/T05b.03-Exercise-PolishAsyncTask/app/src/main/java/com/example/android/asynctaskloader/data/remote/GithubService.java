package com.example.android.asynctaskloader.data.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pablo Farias on 12-03-17.
 */

public interface GithubService {
    @GET("/users/{user}")
    Call<String> getUser(@Path("user") String user);
}
