/*
 * Copyright (C) 2015 The Android Open Source Project
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
package com.example.android.sunshine.data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
<<<<<<< HEAD
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
=======
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceFragmentCompat;
>>>>>>> examples

/**
 * This class serves as the ContentProvider for all of Sunshine's data. This class allows us to
 * bulkInsert data, query data, and delete data.
 * <p>
 * Although ContentProvider implementation requires the implementation of additional methods to
 * perform single inserts, updates, and the ability to get the type of the data from a URI.
 * However, here, they are not implemented for the sake of brevity and simplicity. If you would
 * like, you may implement them on your own. However, we are not going to be teaching how to do
 * so in this course.
 */
public class WeatherProvider extends ContentProvider {

<<<<<<< HEAD
//  TODO (5) Create static constant integer values named CODE_WEATHER & CODE_WEATHER_WITH_DATE to identify the URIs this ContentProvider can handle

//  TODO (7) Instantiate a static UriMatcher using the buildUriMatcher method

    WeatherDbHelper mOpenHelper;

//  TODO (6) Write a method called buildUriMatcher where you match URI's to their numeric ID

//  TODO (1) Implement onCreate
    @Override
    public boolean onCreate() {
//      TODO (2) Within onCreate, instantiate our mOpenHelper

//      TODO (3) Return true from onCreate to signify success performing setup
        return false;
=======
//  DONE (5) Create constant integer values to identify the URIs this ContentProvider can handle
    public static final int CODE_WEATHER = 100;
    public static final int CODE_WEATHER_WITH_DATE = 101;

//  DONE (7) Instantiate a static UriMatcher using the buildUriMatcher method
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    WeatherDbHelper mOpenHelper;

//  DONE (6) Write a method called buildUriMatcher where you match URI's to their numeric ID
    public static UriMatcher buildUriMatcher() {
        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the contructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
            All paths added to the UriMatcher have a corresponding int.
            For each kind of uri you may want to access, ad the corresponding match with addURI.
            The two calls below add matches for the task directory and a single item by ID.
         */
        uriMatcher.addURI(WeatherContract.CONTENT_AUTHORITY, WeatherContract.PATH_WEATHER, CODE_WEATHER);
        uriMatcher.addURI(
                WeatherContract.CONTENT_AUTHORITY,
                WeatherContract.PATH_WEATHER + "/#",
                CODE_WEATHER_WITH_DATE);
        return uriMatcher;
    }

//  DONE (1) Implement onCreate
    @Override
    public boolean onCreate() {
//      DONE (2) Within onCreate, instantiate our mOpenHelper
        Context context = getContext();
        mOpenHelper = new WeatherDbHelper(context);

//      DONE (3) Return true from onCreate to signify success performing setup
        return true;
>>>>>>> examples
    }

    /**
     * Handles requests to insert a set of new rows. In Sunshine, we are only going to be
     * inserting multiple rows of data at a time from a weather forecast. There is no use case
     * for inserting a single row of data into our ContentProvider, and so we are only going to
     * implement bulkInsert. In a normal ContentProvider's implementation, you will probably want
     * to provide proper functionality for the insert method as well.
     *
     * @param uri    The content:// URI of the insertion request.
     * @param values An array of sets of column_name/value pairs to add to the database.
     *               This must not be {@code null}.
     *
     * @return The number of values that were inserted.
     */
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        throw new RuntimeException("Student, you need to implement the bulkInsert mehtod!");
    }

<<<<<<< HEAD
//  TODO (8) Provide an implementation for the query method
=======
//  DONE (8) Provide an implementation for the query method
>>>>>>> examples
    /**
     * Handles query requests from clients. We will use this method in Sunshine to query for all
     * of our weather data as well as to query for the weather on a particular day.
     *
     * @param uri           The URI to query
     * @param projection    The list of columns to put into the cursor. If null, all columns are
     *                      included.
     * @param selection     A selection criteria to apply when filtering rows. If null, then all
     *                      rows are included.
     * @param selectionArgs You may include ?s in selection, which will be replaced by
     *                      the values from selectionArgs, in order that they appear in the
     *                      selection.
     * @param sortOrder     How the rows in the cursor should be sorted.
     * @return A Cursor containing the results of the query. In our implementation,
     */
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
<<<<<<< HEAD
        throw new RuntimeException("Student, implement the query method!");

//      TODO (9) Handle queries on both the weather and weather with date URI

//      TODO (10) Call setNotificationUri on the cursor and then return the cursor
=======
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        // Write URI match code and set a variable to return a Cursor
        int match = sUriMatcher.match(uri);
        Cursor retCursor;
        String tableName = WeatherContract.WeatherEntry.TABLE_NAME;

//      DONE (9) Handle queries on both the weather and weather with date URI
        switch (sUriMatcher.match(uri)) {
            /*
             * When sUriMatcher's match method is called with a URI that looks something like this
             *
             *      content://com.example.android.sunshine/weather/1472214172
             *
             * sUriMatcher's match method will return the code that indicates to us that we need
             * to return the weather for a particular date. The date in this code is encoded in
             * milliseconds and is at the very end of the URI (1472214172) and can be accessed
             * programmatically using Uri's getLastPathSegment method.
             *
             * In this case, we want to return a cursor that contains one row of weather data for
             * a particular date.
             */
            case CODE_WEATHER_WITH_DATE:
                /*
                 * In order to determine the date associated with this URI, we look at the last
                 * path segment. In the comment above, the last path segment is 1472214172 and
                 * represents the number of seconds since the epoch, or UTC time.
                 */
                String normalizedUtcDateString = uri.getLastPathSegment();
                 /*
                 * The query method accepts a string array of arguments, as there may be more
                 * than one "?" in the selection statement. Even though in our case, we only have
                 * one "?", we have to create a string array that only contains one element
                 * because this method signature accepts a string array.
                 */
                selection = WeatherContract.WeatherEntry.COLUMN_DATE + " = ? ";
                selectionArgs = new String[]{normalizedUtcDateString};

                retCursor = db.query(
                        tableName,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case CODE_WEATHER:
                retCursor = db.query(
                        tableName,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
//      DONE (10) Call setNotificationUri on the cursor and then return the cursor
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
>>>>>>> examples
    }

    /**
     * Deletes data at a given URI with optional arguments for more fine tuned deletions.
     *
     * @param uri           The full URI to query
     * @param selection     An optional restriction to apply to rows when deleting.
     * @param selectionArgs Used in conjunction with the selection statement
     * @return The number of rows deleted
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        throw new RuntimeException("Student, you need to implement the delete method!");
    }

    /**
     * In Sunshine, we aren't going to do anything with this method. However, we are required to
     * override it as WeatherProvider extends ContentProvider and getType is an abstract method in
     * ContentProvider. Normally, this method handles requests for the MIME type of the data at the
     * given URI. For example, if your app provided images at a particular URI, then you would
     * return an image URI from this method.
     *
     * @param uri the URI to query.
     * @return nothing in Sunshine, but normally a MIME type string, or null if there is no type.
     */
    @Override
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("We are not implementing getType in Sunshine.");
    }

    /**
     * In Sunshine, we aren't going to do anything with this method. However, we are required to
     * override it as WeatherProvider extends ContentProvider and insert is an abstract method in
     * ContentProvider. Rather than the single insert method, we are only going to implement
     * {@link WeatherProvider#bulkInsert}.
     *
     * @param uri    The URI of the insertion request. This must not be null.
     * @param values A set of column_name/value pairs to add to the database.
     *               This must not be null
     * @return nothing in Sunshine, but normally the URI for the newly inserted item.
     */
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        throw new RuntimeException(
                "We are not implementing insert in Sunshine. Use bulkInsert instead");
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new RuntimeException("We are not implementing update in Sunshine");
    }

    /**
     * You do not need to call this method. This is a method specifically to assist the testing
     * framework in running smoothly. You can read more at:
     * http://developer.android.com/reference/android/content/ContentProvider.html#shutdown()
     */
    @Override
    @TargetApi(11)
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }
}