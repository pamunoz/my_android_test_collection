package com.pfariasmunoz.mysqldbdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    private EditText mEditTextNewName;
    private EditText mEditTextNewUserName;
    private EditText mEditTextNewPassword;

    private String mNewName, mNewUserName, mNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEditTextNewName = (EditText) findViewById(R.id.edit_text_new_name);
        mEditTextNewUserName = (EditText) findViewById(R.id.edit_text_new_user_name);
        mEditTextNewPassword = (EditText) findViewById(R.id.new_user_password);

    }

    public void userRegister(View view) {
        mNewName = mEditTextNewName.getText().toString();
        mNewUserName = mEditTextNewUserName.getText().toString();
        mNewPassword = mEditTextNewPassword.getText().toString();

        String method = "register";
        BackgroundTask task = new BackgroundTask(this);
        task.execute(method, mNewName, mNewUserName, mNewPassword);
    }
}
