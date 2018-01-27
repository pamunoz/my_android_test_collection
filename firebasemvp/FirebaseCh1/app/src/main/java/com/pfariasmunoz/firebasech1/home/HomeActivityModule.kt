package com.pfariasmunoz.firebasech1.home

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {
    @Provides
    fun provideMessageRef(db: FirebaseDatabase): DatabaseReference = db.getReference("message")
}