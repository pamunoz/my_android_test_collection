package com.pfariasmunoz.quakereport;

/**
 * Created by Pablo Farias on 01-11-16.
 */

public class Earthquake {

    private String  mMagnitude;
    private String mLocation;
    private String mTime;

    public Earthquake(String  mMagnitude, String mLocation, String mTime) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mTime = mTime;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(String  mMagnitude) {
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
