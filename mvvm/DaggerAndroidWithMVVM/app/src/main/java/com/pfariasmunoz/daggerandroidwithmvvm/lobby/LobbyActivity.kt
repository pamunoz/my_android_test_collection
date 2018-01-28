package com.pfariasmunoz.daggerandroidwithmvvm.lobby

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pfariasmunoz.daggerandroidwithmvvm.R
import javax.inject.Inject

class LobbyActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : LobbyViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)
    }
}
