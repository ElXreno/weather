package com.elxreno.weather.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.views.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    fun onFabClick() {
        viewState.showSnackBar()
    }

}