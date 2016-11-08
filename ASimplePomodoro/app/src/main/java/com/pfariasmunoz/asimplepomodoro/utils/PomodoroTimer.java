package com.pfariasmunoz.asimplepomodoro.utils;

import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Pablo Farias on 07-11-16.
 */

public class PomodoroTimer {
    private int mSeconds;
    private int mMinutes;
    private int mHours;

    private int mPomodoros;

    private boolean mRunning;
    private boolean mWasRunning;

    public PomodoroTimer(int mPomodoros) {
        this.mPomodoros = mPomodoros;
    }

    public void start() {
        mRunning = true;
    }

    public void stop() {
        mWasRunning = mRunning;
        mSeconds = 0;
        mRunning = false;
    }

    public void pause() {
        mWasRunning = mRunning;
        mRunning = false;
    }

    public void runTimer(final TextView textView) {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mHours = mSeconds / 3600;
                mMinutes = (mSeconds % 3600) / 60;
                int secs = mSeconds % 60;
                String time = String.format("%02d: %02d", mMinutes, secs);
                textView.setText(time);
                if (mRunning) {



                    mSeconds++;
                    if (mSeconds % 60 == 0) {
                        mPomodoros--;
                    }
                }
                if (mPomodoros < 1) {
                    mRunning = false;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public int getSeconds() {
        return mSeconds;
    }

    public void setSeconds(int mSeconds) {
        this.mSeconds = mSeconds;
    }

    public int getMinutes() {
        return mMinutes;
    }

    public void setMinutes(int mMinutes) {
        this.mMinutes = mMinutes;
    }

    public int getHours() {
        return mHours;
    }

    public void setHours(int mHours) {
        this.mHours = mHours;
    }

    public int getPomodoros() {
        return mPomodoros;
    }

    public void setPomodoros(int mPomodoros) {
        this.mPomodoros = mPomodoros;
    }

    public void setRunning(boolean mRunning) {
        this.mRunning = mRunning;
    }

    public void setWasRunning(boolean mWasRunning) {
        this.mWasRunning = mWasRunning;
    }

    public boolean isRunning() {
        return mRunning;
    }

    public boolean isWasRunning() {
        return mWasRunning;
    }
}
