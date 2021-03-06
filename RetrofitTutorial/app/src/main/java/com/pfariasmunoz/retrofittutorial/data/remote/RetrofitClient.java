package com.pfariasmunoz.retrofittutorial.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pablo Farias on 04-03-17.
 */

public class RetrofitClient {

    private static Retrofit sRetrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
