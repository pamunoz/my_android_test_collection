package com.pfariasmunoz.quakereport;

/**
 * Created by Pablo Farias on 01-11-16.
 */

public class Earthquake {
    /** Magnitude of the earthquake */
    private double mMagnitude;
    /** the location of the earthquake */
    private String mLocation;
    /** time of the earthquake */
    private long mTimeInMilliseconds;

    private String mUrl;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude (size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *  earthquake happened
     */
    public Earthquake(double  magnitude, String location, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(double mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setTime(long mTime) {
        this.mTimeInMilliseconds = mTime;
    }

    public String getUrl() {
        return mUrl;
    }
}
