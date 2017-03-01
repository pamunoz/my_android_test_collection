package com.example.android.sunshine;

<<<<<<< HEAD
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
=======
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
>>>>>>> examples

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

<<<<<<< HEAD
        // TODO (2) Display the weather forecast that was passed from MainActivity
=======
        // DONE (2) Display the weather forecast that was passed from MainActivity
        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            TextView displayTextView = (TextView) findViewById(R.id.weather_display);
            displayTextView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
>>>>>>> examples
    }
}