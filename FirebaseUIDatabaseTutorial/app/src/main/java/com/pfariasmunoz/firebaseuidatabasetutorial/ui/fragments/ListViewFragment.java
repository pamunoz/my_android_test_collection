package com.pfariasmunoz.firebaseuidatabasetutorial.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pfariasmunoz.firebaseuidatabasetutorial.R;


public class ListViewFragment extends Fragment {

    private View mRootView;

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        initializeView();
        return mRootView;
    }

    private void initializeView() {
    }

}
