package com.pfariasmunoz.retrofittutorial;

import com.pfariasmunoz.retrofittutorial.data.remote.RetrofitClient;
import com.pfariasmunoz.retrofittutorial.data.remote.SOService;

/**
 * Created by Pablo Farias on 05-03-17.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
