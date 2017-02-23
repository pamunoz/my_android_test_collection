package com.pfariasmunoz.newsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Article>> {

    private ArticleAdapter articleAdapter;
    private ListView articleListView;
    private TextView emptyListView;
    private ProgressBar loadingIndicator;

    /** URL for earthquake data from the USGS dataset */
    private static final String THE_GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search";

    private static final String TEMP_URL = "http://content.guardianapis.com/search?section=politics&page-size=10&show-field=headline,short-url&show-tags=contributor&api-key=d97b8466-d780-4368-9e65-0da55b17b11c";

    public static final String LOG_TAG = MainActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int ARTICLE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
        loadingIndicator.setIndeterminate(true);

        // Create a new {@link ArrayAdapter} of earthquakes
        articleAdapter = new ArticleAdapter(MainActivity.this, new ArrayList<Article>());
        // Find a reference to the {@link ListView} in the layout
        articleListView = (ListView) findViewById(R.id.list);

        emptyListView = (TextView) findViewById(R.id.empty_element);
        articleListView.setEmptyView(emptyListView);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        articleListView.setAdapter(articleAdapter);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // fetch data
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            loadingIndicator.setVisibility(View.GONE);
            // display error
            emptyListView.setText("No hay internet");
        }
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(THE_GUARDIAN_REQUEST_URL);

        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("section", "politics");
        uriBuilder.appendQueryParameter("page-size", "10");
        uriBuilder.appendQueryParameter("show-field", "headline,short-url");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", "d97b8466-d780-4368-9e65-0da55b17b11c");

        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {

        loadingIndicator.setVisibility(View.GONE);
        // Clear the adapter of previous articles data

        emptyListView.setText("No article found");

        articleAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (articles != null && !articles.isEmpty()) {
            articleAdapter.addAll(articles);
        }
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        // Loader reset, so we can clear out our existing data.
        articleAdapter.clear();
    }
}
