package com.pfariasmunoz.fragmentlayouts;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the device is in landscape mode,
        // because if it is, we shut down this activity
        // because this activity is only on portrait mode.
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // shutdown the activity
            finish();
            return;
        }

        // if it is not on portrait mode, we provide the activity with the info it needs
        if (savedInstanceState == null) {

        }
    }
}
