package com.pfariasmunoz.sistemadeventas.smartlock;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pfariasmunoz.sistemadeventas.R;
import com.pfariasmunoz.sistemadeventas.util.UsernamesAndPasswords;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeCredActivity extends AppCompatActivity {

    @BindView(R.id.username1TextInputLayout)
    TextInputLayout mUsername1TextInputLayout;
    @BindView(R.id.password1TextInputLayout)
    TextInputLayout mPassword1TextInputLayout;
    @BindView(R.id.username2TextInputLayout)
    TextInputLayout mUsername2TextInputLayout;
    @BindView(R.id.password2TextInputLayout)
    TextInputLayout mPassword2TextInputLayout;
    @BindView(R.id.username3TextInputLayout)
    TextInputLayout mUsername3TextInputLayout;
    @BindView(R.id.password3TextInputLayout)
    TextInputLayout mPassword3TextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_cred);

        ButterKnife.bind(this);

        mUsername1TextInputLayout.getEditText().setText(UsernamesAndPasswords.username1);
        mPassword1TextInputLayout.getEditText().setText(UsernamesAndPasswords.password1);
        mUsername2TextInputLayout.getEditText().setText(UsernamesAndPasswords.username2);
        mPassword2TextInputLayout.getEditText().setText(UsernamesAndPasswords.password2);
        mUsername3TextInputLayout.getEditText().setText(UsernamesAndPasswords.username3);
        mPassword3TextInputLayout.getEditText().setText(UsernamesAndPasswords.password3);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernamesAndPasswords.username1 = mUsername1TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.password1 = mPassword1TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.username2 = mUsername2TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.password2 = mPassword2TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.username3 = mUsername3TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.password3 = mPassword3TextInputLayout.getEditText().getText().toString();
                Toast.makeText(v.getContext(), R.string.creds_updated_msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
