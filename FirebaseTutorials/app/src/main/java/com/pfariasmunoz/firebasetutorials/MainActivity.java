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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mMessagesReference;
    private Button mSetMessageButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;
    private Button mSaveMessageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        mMessagesReference = mDatabase.getReference("message");



        mMessageEditText = (EditText) findViewById(R.id.ti_add_message);
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
                String message = mMessageEditText.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    addMessage(message);
                } else {
                    addMessage("No se guardo mensaje");
                }
            }
        });



    }

    private void addMessage(String message) {
        // Write a message to the database
        mMessagesReference.setValue(message);
    }

    private void addNewMessage(String message) {
        // Write a message to the database
        mMessagesReference.push().setValue(message);
    }

    private void getMessage() {
        // Read from the database
        mMessagesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                mMessageTextView.setText(value);
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
