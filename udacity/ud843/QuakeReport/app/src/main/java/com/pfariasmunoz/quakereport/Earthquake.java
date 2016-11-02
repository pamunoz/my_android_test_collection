package com.pfariasmunoz.quakereport;

/**
 * Created by Pablo Farias on 01-11-16.
 */

public class Earthquake {

    private float mMagnitude;
    private String mLocation;
    private String mTime;

    public Earthquake(float mMagnitude, String mLocation, String mTime) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mTime = mTime;
    }

    public float getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(float mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }
}
