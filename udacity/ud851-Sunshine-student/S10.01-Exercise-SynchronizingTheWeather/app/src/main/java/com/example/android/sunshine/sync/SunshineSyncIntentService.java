<<<<<<< HEAD
// TODO (5) Create a new class called SunshineSyncIntentService that extends IntentService
//  TODO (6) Create a constructor that calls super and passes the name of this class
//  TODO (7) Override onHandleIntent, and within it, call SunshineSyncTask.syncWeather
=======
package com.example.android.sunshine.sync;

import android.app.IntentService;
import android.content.Intent;

// DONE (5) Create a new class called SunshineSyncIntentService that extends IntentService
public class SunshineSyncIntentService extends IntentService {
//  DONE (6) Create a constructor that calls super and passes the name of this class

    public SunshineSyncIntentService() {
        super("SunshineSyncIntentService");
    }
    //  DONE (7) Override onHandleIntent, and within it, call SunshineSyncTask.syncWeather
    @Override
    protected void onHandleIntent(Intent intent) {
        SunshineSyncTask.syncWeather(this);
    }
}
>>>>>>> examples
