package com.pfariasmunoz.daggerandroidwithmvvm.lobby

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.pfariasmunoz.daggerandroidwithmvvm.common.domain.interactors.LoadCommonGreetingUseCase
import com.pfariasmunoz.daggerandroidwithmvvm.rx.SchedulersFacade

class LobbyViewModelFactory(
        private val loadCommonGreetingUseCase: LoadCommonGreetingUseCase,
        private val loadLobbyGreetingUseCase: LoadLobbyGreetingUseCase,
        private val schedulersFacade: SchedulersFacade) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LobbyViewModel::class.java)) {
            return LobbyViewModel(loadCommonGreetingUseCase, loadLobbyGreetingUseCase, schedulersFacade) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}