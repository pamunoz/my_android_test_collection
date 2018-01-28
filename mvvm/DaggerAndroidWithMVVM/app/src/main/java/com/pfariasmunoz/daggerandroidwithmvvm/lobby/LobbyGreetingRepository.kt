package com.pfariasmunoz.daggerandroidwithmvvm.lobby

import io.reactivex.Single

class LobbyGreetingRepository {
    fun getGreeting(): Single<String> = Single.just("Hello from LobbyGreetingRepository")
}
