package com.pfariasmunoz.firebaseuidatabasetutorial.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pfariasmunoz.firebaseuidatabasetutorial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexRecyclerViewFragment extends Fragment {


    public IndexRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_recycler_view, container, false);
    }

}
