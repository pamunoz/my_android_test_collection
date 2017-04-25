package com.pfariasmunoz.fragmentlayouts;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Pablo Farias on 25-04-17.
 */

public class DetailsFragment extends Fragment {

    public static DetailsFragment getInstance(int index) {
        DetailsFragment f = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;

    }

    public int getShowIndex() {
        return 0;
    }
}
