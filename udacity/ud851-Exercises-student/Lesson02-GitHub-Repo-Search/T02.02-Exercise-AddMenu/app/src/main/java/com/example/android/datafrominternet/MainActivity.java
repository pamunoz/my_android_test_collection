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
package com.example.android.datafrominternet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_search_box)
    EditText mSearchBoxEditText;
    @BindView(R.id.tv_url_display)
    TextView mUrlDisplayTextView;
    @BindView(R.id.tv_github_search_results_json)
    TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind view with butterknife
        ButterKnife.bind(this);

    }

    // Do 2 - 7 in menu.xml ///////////////////////////////////////////////////////////////////////
    // DONE (2) Create a menu in xml called main.xml
    // DONE (3) Add one menu item to your menu
    // DONE (4) Give the menu item an id of @+id/action_search
    // DONE (5) Set the orderInCategory to 1
    // DONE (6) Show this item if there is room (use app:showAsAction, not android:showAsAction)
    // DONE (7) Set the title to the search string ("Search") from strings.xml
    // Do 2 - 7 in menu.xml ///////////////////////////////////////////////////////////////////////


    // DONE (8) Override onCreateOptionsMenu
    // DONE (9) Within onCreateOptionsMenu, use getMenuInflater().inflate to inflate the menu
    // DONE (10) Return true to display your menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    // DONE (11) Override onOptionsItemSelected
    // DONE (12) Within onOptionsItemSelected, get the ID of the item that was selected
    // DONE (13) If the item's ID is R.id.action_search, show a Toast and return true to tell Android that you've handled this menu click
    // DONE (14) Don't forgot to call .show() on your Toast
    // DONE (15) If you do NOT handle the menu click, return super.onOptionsItemSelected to let Android handle the menu click


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_search) {
            String message = "search menu selected";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
