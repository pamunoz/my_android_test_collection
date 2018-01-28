package com.pfariasmunoz.daggerandroidwithmvvm.lobby

import io.reactivex.Single
import javax.inject.Inject

class LoadLobbyGreetingUseCase
@Inject constructor(private val greetingRepository: LobbyGreetingRepository){
    fun execute(): Single<String> = greetingRepository.getGreeting()
}