package com.pfariasmunoz.appindenandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClientDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CLIENT_KEY = "client_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);
    }
}
