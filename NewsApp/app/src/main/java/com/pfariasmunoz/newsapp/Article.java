package com.pfariasmunoz.newsapp;

import java.util.Date;

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
    private String mDateOfPublication;

    private String mUrl;

    public Article(String title, String section, String authorName, String dateOfPublication, String url) {
        this.mTitle = title;
        this.mSection = section;
        this.mAuthorName = authorName;
        this.mDateOfPublication = dateOfPublication;
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

    public String getDateOfPublication() {
        return mDateOfPublication;
    }

    public void setTimeInMilliseconds(String dateOfPublication) {
        this.mDateOfPublication = dateOfPublication;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
