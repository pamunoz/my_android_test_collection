package com.pfariasmunoz.fragmentlayouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Pablo Farias on 25-04-17.
 */

public class DetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(getActivity());
        TextView heroDataTextView = new TextView(getActivity());
        // a way to specify dimmention using specific units
        int padding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16,
                getActivity().getResources().getDisplayMetrics());
        // apply the padding to the textview in all its sides
        heroDataTextView.setPadding(padding, padding, padding, padding);
        // add the textview to the scroolview
        scrollView.addView(heroDataTextView);
        // set the text from the helpler class for the hero
        // in the specific index selected
        heroDataTextView.setText(SuperHeroInfo.HISTORY[getShowIndex()]);
        // return the scrollview that contains the textview
        return scrollView;
    }

    public static DetailsFragment getInstance(int index) {
        DetailsFragment f = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;

    }

    public int getShowIndex() {
        return getArguments().getInt("index", 0);
    }
}
