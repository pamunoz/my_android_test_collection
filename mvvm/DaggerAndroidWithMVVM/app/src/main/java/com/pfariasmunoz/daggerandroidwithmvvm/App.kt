package com.pfariasmunoz.daggerandroidwithmvvm

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber

class App : Application(), HasActivityInjector {

    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
    override fun activityInjector(): AndroidInjector<Activity> = androidInjector



}