package com.pfariasmunoz.appindenandroid.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Pablo Farias on 12-04-17.
 */

public class RecentClinetsFragment extends ClientListFragment {


    public RecentClinetsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_clients_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentClientsQuery = databaseReference.child("posts")
                .limitToFirst(100);
        // [END recent_posts_query]

        return recentClientsQuery;
    }
}
