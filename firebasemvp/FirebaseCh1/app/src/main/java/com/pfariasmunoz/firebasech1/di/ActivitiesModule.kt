package com.pfariasmunoz.firebasech1.di

import com.pfariasmunoz.firebasech1.home.HomeActivity
import com.pfariasmunoz.firebasech1.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindHomeActivity(): HomeActivity
}