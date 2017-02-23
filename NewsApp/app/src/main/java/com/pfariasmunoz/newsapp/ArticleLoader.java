package com.pfariasmunoz.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Pablo Farias on 25-01-17.
 * Loads a list of articles by using an AsyncTask to perform the
 * network request to the given URL.
 */

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    /** Tag for log messages */
    private static final String LOG_TAG = ArticleLoader.class.getName();

    /** Query URL */
    String mUrl;


    /**
     * Constructs a new {@link ArticleLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public ArticleLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Article> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl == null) {
            return null;
        }
        List<Article> articles = QueryUtils.fetchArticleData(mUrl);
        return articles;
    }

    /**
     * method to call forceLoad() which is a required step to actually
     * trigger the loadInBackground() method to execute.
     */
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
