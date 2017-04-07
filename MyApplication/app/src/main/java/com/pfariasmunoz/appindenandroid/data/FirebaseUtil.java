package com.pfariasmunoz.appindenandroid.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Pablo Farias on 06-04-17.
 */

public class FirebaseUtil {
    public static FirebaseDatabase sDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference mClientDbReference;


    private FirebaseUtil() {
    }

    public static DatabaseReference getClientsReference() {
        return mClientDbReference = sDatabase.getReference().child("clientes");
    }

}
