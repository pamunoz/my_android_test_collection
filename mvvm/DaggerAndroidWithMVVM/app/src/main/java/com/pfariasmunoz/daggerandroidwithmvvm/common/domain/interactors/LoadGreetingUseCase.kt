package com.pfariasmunoz.daggerandroidwithmvvm.common.domain.interactors

import io.reactivex.Single

interface LoadGreetingUseCase {
    fun execute(): Single<String>
}
