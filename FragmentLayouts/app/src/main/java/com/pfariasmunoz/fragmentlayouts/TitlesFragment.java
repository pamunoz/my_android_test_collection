package com.pfariasmunoz.fragmentlayouts;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Pablo Farias on 25-04-17.
 */

public class TitlesFragment extends ListFragment {

    boolean mHorizontalMode;
    int mCurrentCheckPosition = 0;
    public static final String CURRENT_POSITION_KEY = "current_position";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> connectArrayToListView =
                new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                SuperHeroInfo.NAMES);

        setListAdapter(connectArrayToListView);

        View detailsFrameLayout = getActivity().findViewById(R.id.fl_details);

        // check if we are horizontal or portrait mode
        mHorizontalMode = detailsFrameLayout != null && detailsFrameLayout.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurrentCheckPosition = savedInstanceState.getInt(CURRENT_POSITION_KEY, 0);
        }

        if (mHorizontalMode) {
            // highlight the current selected superhero
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showHeroDetails(mCurrentCheckPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the hero's position selected
        outState.putInt(CURRENT_POSITION_KEY, mCurrentCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showHeroDetails(position);
    }

    private void showHeroDetails(int position) {
        mCurrentCheckPosition = position;

        if (mHorizontalMode) {
            // Highlight the current element of the listView
            getListView().setItemChecked(position, true);

            DetailsFragment details =
                    (DetailsFragment) getFragmentManager()
                            .findFragmentById(R.id.fl_details);
            if (details == null || details.getShowIndex() != position) {
                // if the index(position) has not been asing to the details fragment
                // we asing it here.
                details = DetailsFragment.getInstance(position);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fl_details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            } else {
                Intent intent = new Intent();
                intent.putExtra("index", position);
                intent.setClass(getActivity(), DetailsActivity.class);
                startActivity(intent);
            }
        }
    }
}
