package com.pfariasmunoz.mysqldbdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userLogin(View view) {
        Toast.makeText(this, "This is awesome yeah", Toast.LENGTH_SHORT).show();
    }

    public void userRegister(View view) {
        Intent registerIntent = new Intent(this, Register.class);
        startActivity(registerIntent);
    }
}
