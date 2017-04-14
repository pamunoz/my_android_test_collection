package com.pfariasmunoz.firebasetutorials;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mMessagesReference;
    private Button mSetMessageButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;
    private Button mSaveMessageButton;
    private EditText mEmailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        mMessagesReference = mDatabase.getReference("users");



        mMessageEditText = (EditText) findViewById(R.id.et_user_name);
        mEmailEditText = (EditText) findViewById(R.id.et_user_email);
        mMessageTextView = (TextView) findViewById(R.id.tv_message);
        mSetMessageButton = (Button) findViewById(R.id.set_message);
        mSetMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMessage();
            }
        });

        mSaveMessageButton = (Button) findViewById(R.id.bt_save_message);
        mSaveMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mMessageEditText.getText().toString().trim();
                String email = mEmailEditText.getText().toString().trim();

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);

                if (!TextUtils.isEmpty(name)) {
                    addNewUser(dataMap);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to add User", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void addUserUser(HashMap<String, String> data) {
        // Write a message to the database
        mMessagesReference.setValue(data);
    }

    private void addNewUser(HashMap<String, String> data) {
        // Write a message to the database
        mMessagesReference.push().setValue(data);
    }

    private void getMessage() {
        // Read from the database
        mMessagesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();
                String completeText = key + ": " + value;
                mMessageTextView.setText(completeText);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
