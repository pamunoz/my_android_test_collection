package com.pfariasmunoz.firebaseuidatabasetutorial.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Pablo Farias on 14-04-17.
 */

public class Utils {





    public static String getPostUID() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mPostDBReference = mDatabase.getReference(Constants.TABLE_POST);
        String url = mPostDBReference.push().toString();
        return url.substring(url.lastIndexOf("/"), url.length());
    }
}
