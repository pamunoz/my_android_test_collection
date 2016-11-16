package com.pfariasmunoz.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Pablo Farias on 16-11-16.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{
    String stringUrl;


    public EarthquakeLoader(Context context) {
        super(context);
    }


    @Override
    public List<Earthquake> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (stringUrl.length() < 1 || stringUrl == null) {
            return null;
        }
        List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(stringUrl);
        return earthquakes;
    }

    public void setStringUrl(String stringUrl) {
        this.stringUrl = stringUrl;
    }
}
