package com.pfariasmunoz.daggerandroidwithmvvm.lobby

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.pfariasmunoz.daggerandroidwithmvvm.common.domain.interactors.LoadCommonGreetingUseCase
import com.pfariasmunoz.daggerandroidwithmvvm.common.domain.interactors.LoadGreetingUseCase
import com.pfariasmunoz.daggerandroidwithmvvm.common.viewmodel.Response
import com.pfariasmunoz.daggerandroidwithmvvm.rx.SchedulersFacade

import io.reactivex.disposables.CompositeDisposable


class LobbyViewModel(private val loadCommonGreetingUseCase: LoadCommonGreetingUseCase,
                     private val loadLobbyGreetingUseCase: LoadLobbyGreetingUseCase,
                     private val schedulersFacade: SchedulersFacade) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val response = MutableLiveData<Response>()

    override fun onCleared() {
        disposables.clear()
    }

    fun loadCommonGreeting() {
        loadGreeting(loadCommonGreetingUseCase)
    }

    fun loadLobbyGreeting() {
        loadGreeting(loadLobbyGreetingUseCase)
    }

    fun response(): MutableLiveData<Response> {
        return response
    }

    private fun loadGreeting(loadGreetingUseCase: LoadGreetingUseCase) {
        disposables.add(loadGreetingUseCase.execute()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe { response.setValue(Response.loading()) }
                .subscribe(
                        { greeting -> response.setValue(Response.success(greeting)) }
                ) { throwable -> response.setValue(Response.error(throwable)) }
        )
    }
}
