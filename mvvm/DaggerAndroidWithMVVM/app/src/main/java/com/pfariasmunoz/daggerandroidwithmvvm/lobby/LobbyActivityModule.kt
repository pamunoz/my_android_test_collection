package com.pfariasmunoz.daggerandroidwithmvvm.lobby

import com.pfariasmunoz.daggerandroidwithmvvm.common.domain.interactors.LoadCommonGreetingUseCase
import com.pfariasmunoz.daggerandroidwithmvvm.rx.SchedulersFacade
import dagger.Module
import dagger.Provides

@Module
class LobbyActivityModule {
    @Provides
    fun provideLobbyGreetingRepository() = LobbyGreetingRepository()

    @Provides
    fun provideLobbyViewModelFactory(
            loadCommonGreetingUseCase: LoadCommonGreetingUseCase,
            loadLobbyGreetingUseCase: LoadLobbyGreetingUseCase,
            schedulersFacade: SchedulersFacade) : LobbyViewModelFactory {
        return LobbyViewModelFactory(
                loadCommonGreetingUseCase,
                loadLobbyGreetingUseCase,
                schedulersFacade)
    }
}
