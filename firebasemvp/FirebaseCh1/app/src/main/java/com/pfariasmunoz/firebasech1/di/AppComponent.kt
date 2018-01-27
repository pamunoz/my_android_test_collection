package com.pfariasmunoz.firebasech1.di

import com.pfariasmunoz.firebasech1.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivitiesModule::class])
interface AppComponent {
    fun inject(app: App)
}