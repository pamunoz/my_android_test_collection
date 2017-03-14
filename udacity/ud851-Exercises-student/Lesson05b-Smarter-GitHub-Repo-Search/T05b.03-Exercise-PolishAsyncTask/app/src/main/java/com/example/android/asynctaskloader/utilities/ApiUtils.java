package com.example.android.asynctaskloader.utilities;

import com.example.android.asynctaskloader.data.remote.GithubService;
import com.example.android.asynctaskloader.data.remote.RetrofitClient;

/**
 * Created by Pablo Farias on 14-03-17.
 */

public class ApiUtils {
    public static final String GITHUB_BASE_URL = "https://api.github.com/";

    public static GithubService getGithubService() {
        return RetrofitClient.getClient(GITHUB_BASE_URL).create(GithubService.class);
    }
}
