package com.pfariasmunoz.mysqldbdemo;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Pablo Farias on 06-02-17.
 */

public class BackgroundTask extends AsyncTask<String , Void, Void> {

    private Context mContext;

    public BackgroundTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String ... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
