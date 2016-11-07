package com.pfariasmunoz.buttonclickcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private Button button;
    private TextView textView;
    private int numTimesClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = "";
                numTimesClicked++;
                if (numTimesClicked == 1) {
                    time = "time";
                } else {
                    time = "times";
                }
                String result ="\nThe button got tapped " + numTimesClicked + " " + time;
                textView.append(result);
            }
        };

        button.setOnClickListener(ourOnClickListener);
    }
}
