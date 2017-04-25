package com.pfariasmunoz.fragmentlayouts;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

/**
 * Created by Pablo Farias on 25-04-17.
 */

public class TitlesFragment extends ListFragment {

    boolean mDuelPane;
    int CurrentCheckPosition = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> connectArrayToListView =
                new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                SuperHeroInfo.NAMES);
    }
}
