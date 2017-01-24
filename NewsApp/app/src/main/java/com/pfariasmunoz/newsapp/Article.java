package com.pfariasmunoz.newsapp;

/**
 * Created by Pablo Farias on 24-01-17.
 */

public class Article {

    /** The title of the Article */
    private String mTitle;

    /** The name of the sections the article belongs to */
    private String mSection;

    /** (optional) The name of the author */
    private String mAuthorName;

    /** (optional) The date of publication */
    private long mTimeInMilliseconds;

    private String mUrl;

    public Article(String title, String section, String authorName, long timeInMilliseconds, String url) {
        this.mTitle = title;
        this.mSection = section;
        this.mAuthorName = authorName;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        this.mSection = section;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        this.mAuthorName = authorName;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setTimeInMilliseconds(long mTimeInMilliseconds) {
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
