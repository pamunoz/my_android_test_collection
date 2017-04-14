package com.pfariasmunoz.firebaseuidatabasetutorial.data.models;

/**
 * Created by Pablo Farias on 14-04-17.
 */

public class Post {

    private String UID;
    private String text;

    public Post() {
    }

    public Post(String UID, String text) {
        this.UID = UID;
        this.text = text;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
