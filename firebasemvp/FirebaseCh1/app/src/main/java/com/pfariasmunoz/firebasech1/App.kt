package com.pfariasmunoz.firebasech1

import android.app.Activity
import android.app.Application
import com.pfariasmunoz.firebasech1.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector : DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}