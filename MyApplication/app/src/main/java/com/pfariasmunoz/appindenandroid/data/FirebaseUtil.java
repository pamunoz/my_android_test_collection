package com.pfariasmunoz.appindenandroid.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Pablo Farias on 06-04-17.
 */

public class FirebaseUtil {
    private static FirebaseDatabase mFirebaseDatabase = createDatabase();
    private static DatabaseReference mClientDbReference;


    private FirebaseUtil() {
    }

    public static DatabaseReference getClientsReference() {
        return mClientDbReference = mFirebaseDatabase.getReference().child("clientes");
    }

    private static FirebaseDatabase createDatabase() {
        return FirebaseDatabase.getInstance();
    }


}
