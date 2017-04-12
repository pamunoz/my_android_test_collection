package com.pfariasmunoz.appindenandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pfariasmunoz.appindenandroid.ClientDetailActivity;
import com.pfariasmunoz.appindenandroid.R;
import com.pfariasmunoz.appindenandroid.data.models.Client;
import com.pfariasmunoz.appindenandroid.viewholder.ClientViewHolder;

/**
 * Created by Pablo Farias on 12-04-17.
 */

public abstract class ClientListFragment extends Fragment {

    private static final String TAG = ClientListFragment.class.getSimpleName();

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Client, ClientViewHolder> mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    public ClientListFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_all_clients, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.client_list);
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query clientsQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<Client, ClientViewHolder>(
                Client.class,
                R.layout.item_client,
                ClientViewHolder.class,
                clientsQuery) {
            @Override
            protected void populateViewHolder(final ClientViewHolder viewHolder,
                                              final Client model,
                                              final int position) {
                final DatabaseReference clientsreference = getRef(position);

                // Set click listener for the whole client view
                final String postKey = clientsreference.getKey();

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch ClientDetailActivity
                        Intent intent = new Intent(getActivity(), ClientDetailActivity.class);
                        intent.putExtra(ClientDetailActivity.EXTRA_CLIENT_KEY, postKey);
                        startActivity(intent);
                    }
                });



            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
