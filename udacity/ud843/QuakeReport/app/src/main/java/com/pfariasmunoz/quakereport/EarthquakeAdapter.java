package com.pfariasmunoz.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pablo Farias on 01-11-16.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        // set the text Magnitude
        TextView magTextView = (TextView) listItemView.findViewById(R.id.magnitude);

        String mag = String.valueOf(currentEarthquake.getMagnitude());
        magTextView.setText(mag);

        // set the text Location

        TextView locTextView = (TextView) listItemView.findViewById(R.id.location);

        locTextView.setText(currentEarthquake.getLocation());

        // set the text Time

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        timeTextView.setText(currentEarthquake.getTime());


        return listItemView;
    }
}
