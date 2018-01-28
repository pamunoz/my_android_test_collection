package com.pfariasmunoz.daggerandroidwithmvvm.common.domain.model

import io.reactivex.Single

class CommonGreetingRepository {
    fun getGreetingRepository() : Single<String> = Single.just("Hello from CommonGreetingRepository")
}