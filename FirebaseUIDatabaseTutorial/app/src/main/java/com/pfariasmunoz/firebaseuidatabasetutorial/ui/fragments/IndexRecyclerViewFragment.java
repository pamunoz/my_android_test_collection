package com.pfariasmunoz.firebaseuidatabasetutorial.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.pfariasmunoz.firebaseuidatabasetutorial.R;
import com.pfariasmunoz.firebaseuidatabasetutorial.data.models.Post;
import com.pfariasmunoz.firebaseuidatabasetutorial.utils.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexRecyclerViewFragment extends Fragment {

    private View mRootView;
    private RecyclerView mIndexRecyclerView;
    private FirebaseIndexRecyclerAdapter<Post, RecyclerViewFragment.PostViewHolder> mPostIndexAdapter;
    private DatabaseReference mPostIndexDbReference;
    private String[] mEmailList = Constants.EMAIL_LIST;


    public IndexRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_index_recycler_view, container, false);
        initializeView();
        return mRootView;
    }

    private void initializeView() {
        int viewId = R.id.indexedRecyclerView;
        mIndexRecyclerView = (RecyclerView) mRootView.findViewById(viewId);
    }

}
