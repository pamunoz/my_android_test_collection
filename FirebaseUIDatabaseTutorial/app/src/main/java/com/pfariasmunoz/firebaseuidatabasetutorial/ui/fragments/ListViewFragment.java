package com.pfariasmunoz.firebaseuidatabasetutorial.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pfariasmunoz.firebaseuidatabasetutorial.R;
import com.pfariasmunoz.firebaseuidatabasetutorial.utils.Constants;
import com.pfariasmunoz.firebaseuidatabasetutorial.utils.Utils;


public class ListViewFragment extends Fragment {

    private View mRootView;
    private ListView mListView;
    private FirebaseListAdapter<String> mListAdapter;
    private DatabaseReference mListDatabaseReference;
    private FirebaseDatabase mDatabase;
    private TextView mTextView;

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        // initialize the views and database
        initializeViewAndDb();
        addFloatingActionButton();
        return mRootView;
    }

    private void addFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) mRootView.findViewById(R.id.fab_list_text);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListDatabaseReference.push().setValue("Random text: " + Utils.getPostUID());
            }
        });
    }

    private void initializeViewAndDb() {
        mDatabase = FirebaseDatabase.getInstance();
        mListDatabaseReference = mDatabase.getReference(Constants.LIST);
        mListView = (ListView) mRootView.findViewById(R.id.listview);
        setUpAdapter();
    }

    private void setUpAdapter() {
        mListAdapter = new FirebaseListAdapter<String>(
                getActivity(),
                String.class,
                R.layout.item_text,
                mListDatabaseReference) {
            @Override
            protected void populateView(View v, String model, int position) {
                mTextView = (TextView) v.findViewById(R.id.list_text);
                mTextView.setText(model);
            }
        };
    }

}
