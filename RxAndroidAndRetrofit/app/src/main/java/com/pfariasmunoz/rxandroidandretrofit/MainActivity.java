package com.pfariasmunoz.rxandroidandretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String API_CALL = "http://api.openweathermap.org/data/2.5/weather?q=Melipilla,cl&appid=a3b82b63ba1e468fac12c6dde27641fa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
