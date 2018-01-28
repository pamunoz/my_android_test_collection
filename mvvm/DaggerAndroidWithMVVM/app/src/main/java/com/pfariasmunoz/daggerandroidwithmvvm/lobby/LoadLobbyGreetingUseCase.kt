package com.pfariasmunoz.daggerandroidwithmvvm.lobby

import com.pfariasmunoz.daggerandroidwithmvvm.common.domain.interactors.LoadGreetingUseCase
import io.reactivex.Single
import javax.inject.Inject

class LoadLobbyGreetingUseCase
@Inject constructor(private val greetingRepository: LobbyGreetingRepository) : LoadGreetingUseCase{
    override fun execute(): Single<String> = greetingRepository.getGreeting()
}