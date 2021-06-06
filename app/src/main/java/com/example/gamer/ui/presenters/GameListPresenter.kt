package com.example.gamer.ui.presenters

import com.example.gamer.core.mapping.toGame
import com.example.gamer.core.network.api.ApiFactory
import com.example.gamer.core.network.models.Params
import com.example.gamer.ui.fragments.MainFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GameListPresenter(
    private val gameListView: GameListView
) {

    private val compositeDisposable = CompositeDisposable()

    fun loadData() {
        val apiFactory = ApiFactory.getInstance()
        val apiService = apiFactory.getApiService()

        val disposable = apiService.getData(
            Params(
                dates = "2010-01-01,2021-05-01",
                ordering = "-rated"
            ).toMap()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val results = it.results
                    val games = results.map { gameVO -> gameVO.toGame() }
                    gameListView.showData(games)
                },
                {
                    gameListView.showError(it)
                }
            )

        compositeDisposable.add(disposable)
    }

    fun dispose() {
        if (compositeDisposable != null)
            compositeDisposable.dispose()
    }
}