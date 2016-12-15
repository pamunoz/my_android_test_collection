/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunshine.data.WeatherContract;
import static com.example.android.sunshine.data.WeatherContract.WeatherEntry;


/**
 * {@link ForecastAdapter} exposes a list of weather forecasts
 * from a {@link android.database.Cursor} to a {@link android.support.v7.widget.RecyclerView}.
 */
class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    //  DONE (14) Remove the mWeatherData declaration and the setWeatherData method
    //  DONE (1) Declare a private final Context field called mContext
    private Context mContext;


    /*
     * Below, we've defined an interface to handle clicks on items within this Adapter. In the
     * constructor of our ForecastAdapter, we receive an instance of a class that has implemented
     * said interface. We store that instance in this variable to call the onClick method whenever
     * an item is clicked in the list.
     */
    final private ForecastAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface ForecastAdapterOnClickHandler {
        void onClick(String weatherForDay);
    }

//  DONE (2) Declare a private Cursor field called mCursor
    private Cursor mCursor;
//  DONE (3) Add a Context field to the constructor and store that context in mContext
    /**
     * Creates a ForecastAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public ForecastAdapter(Context context, ForecastAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
        mContext = context;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.forecast_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ForecastAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param forecastAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder forecastAdapterViewHolder, int position) {
//      DONE (5) Delete the current body of onBindViewHolder
//      DONE (6) Move the cursor to the appropriate position
        mCursor.moveToPosition(position);
//      DONE (7) Generate a weather summary with the date, description, high and low
        int idIndex = mCursor.getColumnIndex(WeatherEntry._ID);
        int dateIndex = mCursor.getColumnIndex(WeatherEntry.COLUMN_DATE);
        int weatherIdIndex = mCursor.getColumnIndex(WeatherEntry.COLUMN_WEATHER_ID);
        int minTempIndex = mCursor.getColumnIndex(WeatherEntry.COLUMN_MIN_TEMP);
        int maxTempIndex = mCursor.getColumnIndex(WeatherEntry.COLUMN_MAX_TEMP);
        int humidityIndex = mCursor.getColumnIndex(WeatherEntry.COLUMN_HUMIDITY);
        int pressureIndex = mCursor.getColumnIndex(WeatherEntry.COLUMN_PRESSURE);
        int windSpeedIndex = mCursor.getColumnIndex(WeatherEntry.COLUMN_WIND_SPEED);
        int directionDegreesIndex =mCursor.getColumnIndex(WeatherEntry.COLUMN_DEGREES);

        int id = mCursor.getInt(idIndex);
        int date = mCursor.getInt(dateIndex);
        int weatherId = mCursor.getInt(weatherIdIndex);
        float minTemp = mCursor.getFloat(minTempIndex);
        float maxTemp = mCursor.getFloat(maxTempIndex);
        float humidity = mCursor.getFloat(humidityIndex);
        float pressure = mCursor.getFloat(pressureIndex);
        float windSpeed = mCursor.getFloat(windSpeedIndex);
        float directionDegrees = mCursor.getFloat(directionDegreesIndex);

        String separator = " - ";
        String wetherSummary =
                date + separator +
                weatherId + separator +
                minTemp + separator +
                maxTemp + separator + "\n" +
                humidity + separator +
                pressure + separator +
                windSpeed + separator +
                directionDegrees;


//      DONE (8) Display the summary that you created above
        // determine the values of the wanted data
        forecastAdapterViewHolder.itemView.setTag(id);
        forecastAdapterViewHolder.weatherSummary.setText(wetherSummary);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
//      DONE (9) Delete the current body of getItemCount
//      DONE (10) If mCursor is null, return 0. Otherwise, return the count of mCursor
        if (null == mCursor) {
            return 0;
        } else {
            return mCursor.getCount();
        }
    }

//  DONE (11) Create a new method that allows you to swap Cursors.
//      DONE (12) After the new Cursor is set, call notifyDataSetChanged
    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView weatherSummary;

        ForecastAdapterViewHolder(View view) {
            super(view);
            weatherSummary = (TextView) view.findViewById(R.id.tv_weather_data);
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         *
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            //  DONE (13) Instead of passing the String from the data array, use the weatherSummary text!
            mClickHandler.onClick(weatherSummary.getText().toString().trim());
        }
    }
}