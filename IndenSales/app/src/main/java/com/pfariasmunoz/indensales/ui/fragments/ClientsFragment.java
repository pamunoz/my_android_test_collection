package com.pfariasmunoz.indensales.ui.fragments;


import android.os.Bundle;
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
    private View mRootView;
    private ProgressBar mLoadingIndicatorProgressBar;

    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_clients, container, false);
        initializeView();

        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mClientAdapter.cleanup();
    }

    private void initializeView() {
        mClientRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_clients);
        mClientRecyclerView.setHasFixedSize(false);
        mClientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mClientRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicatorProgressBar = (ProgressBar) mRootView.findViewById(R.id.pb_loading_indicator);
        setupAdapter();
    }

    private void setupAdapter() {
        mClientAdapter = new FirebaseRecyclerAdapter<Client, ClientViewHolder>(
                Client.class,
                R.layout.item_client,
                ClientViewHolder.class,
                FirebaseDb.sClientsRef) {
            @Override
            protected void populateViewHolder(final ClientViewHolder viewHolder, final Client model, int position) {


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
