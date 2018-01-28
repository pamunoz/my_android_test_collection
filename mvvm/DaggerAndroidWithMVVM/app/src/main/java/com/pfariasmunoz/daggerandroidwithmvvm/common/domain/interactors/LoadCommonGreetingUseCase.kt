package com.pfariasmunoz.daggerandroidwithmvvm.common.domain.interactors

import com.pfariasmunoz.daggerandroidwithmvvm.common.domain.model.CommonGreetingRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadCommonGreetingUseCase
@Inject constructor(private val greetingRepository: CommonGreetingRepository) {

    fun execute() : Single<String> = greetingRepository.getGreeting()

}