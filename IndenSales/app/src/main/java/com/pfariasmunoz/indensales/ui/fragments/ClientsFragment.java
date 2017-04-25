package com.pfariasmunoz.indensales.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.FirebaseDb;
import com.pfariasmunoz.indensales.data.models.Adress;
import com.pfariasmunoz.indensales.data.models.Client;
import com.pfariasmunoz.indensales.ui.viewholders.ClientViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsFragment extends Fragment {

    private RecyclerView mClientRecyclerView;
    private FirebaseRecyclerAdapter<Client, ClientViewHolder> mClientAdapter;
    private ProgressBar mLoadingIndicatorProgressBar;

    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        mClientRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_clients);
        mLoadingIndicatorProgressBar = (ProgressBar) rootView.findViewById(R.id.pb_loading_indicator);
        mClientRecyclerView.setHasFixedSize(false);
        mClientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mClientRecyclerView.setVisibility(View.INVISIBLE);
        setupAdapter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mClientAdapter.cleanup();
    }

    private void setupAdapter() {
        Query clientsRef = FirebaseDb.sClientsRef;
        mClientAdapter = new FirebaseRecyclerAdapter<Client, ClientViewHolder>(
                Client.class,
                R.layout.item_client,
                ClientViewHolder.class,
                clientsRef) {
            @Override
            protected void populateViewHolder(
                    final ClientViewHolder viewHolder,
                    final Client model, int position) {


                String clientUid = FirebaseDb.getUid(getRef(position));
                viewHolder.setTextOnViews(model);
                mLoadingIndicatorProgressBar.setVisibility(View.GONE);
                mClientRecyclerView.setVisibility(View.VISIBLE);
            }
        };
        mClientAdapter.notifyDataSetChanged();
        mClientRecyclerView.setAdapter(mClientAdapter);
    }

}
