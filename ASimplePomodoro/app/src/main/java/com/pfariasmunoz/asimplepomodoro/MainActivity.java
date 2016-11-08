package com.pfariasmunoz.asimplepomodoro;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.pfariasmunoz.asimplepomodoro.utils.PomodoroTimer;

public class MainActivity extends AppCompatActivity {

    private PomodoroTimer pomodoroTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            pomodoroTimer.setSeconds(savedInstanceState.getInt("seconds"));
            pomodoroTimer.setRunning(savedInstanceState.getBoolean("running"));
            pomodoroTimer.setWasRunning(savedInstanceState.getBoolean("wasRunning"));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pomodoroTimer = new PomodoroTimer(1);
        TextView timeView = (TextView) findViewById(R.id.time_textView);
        pomodoroTimer.runTimer(timeView);

        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pomodoroTimer.start();
            }
        });

        Button stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pomodoroTimer.stop();
            }
        });

        Button pauseButton = (Button) findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pomodoroTimer.pause();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", pomodoroTimer.getSeconds());
        savedInstanceState.putBoolean("running", pomodoroTimer.isRunning());
        savedInstanceState.putBoolean("wasRunning", pomodoroTimer.isWasRunning());

    }
}
