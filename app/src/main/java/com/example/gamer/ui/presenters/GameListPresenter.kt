package com.example.gamer.ui.presenters

import com.example.gamer.core.entities.Game
import com.example.gamer.core.mapping.toGame
import com.example.gamer.core.network.api.ApiFactory
import com.example.gamer.core.network.models.Params
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GameListPresenter(
    private val gameListView: GameListView
) {

    private val compositeDisposable = CompositeDisposable()
    private val apiFactory = ApiFactory.getInstance()
    private val apiService = apiFactory.getApiService()

    private var games = listOf<Game>()

    private var page: Int = 1

    fun loadData() {

        val disposable = apiService.getData(
            Params(
                dates = "2010-01-01,2021-06-01",
                ordering = "-rated"
            ).toMap()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val results = it.results
                    this.games = results.map { gameVO -> gameVO.toGame() }
                    gameListView.showData(this.games)
                    page += 1
                },
                { gameListView.showError(it) }
            )


        compositeDisposable.add(disposable)
    }

    fun loadMore() {
        val disposable = apiService.getData(
            Params(
                dates = "2010-01-01,2021-06-01",
                ordering = "-rated",
                page = page
            ).toMap()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val results = it.results
                    val newGames = results.map { gameVO -> gameVO.toGame() }

                    val a = this.games as MutableList<Game>
                    a.addAll(newGames)

                    this.games = a
                    gameListView.showData(this.games)

                    page += 1
                },
                {
                    gameListView.showError(it)
                }
            )

        compositeDisposable.add(disposable)
    }

    fun readyToLoadMore(adapterPosition: Int) {
        if (adapterPosition == this.games.size - 2)
            loadMore()
    }

    fun dispose() {
        if (compositeDisposable != null)
            compositeDisposable.dispose()
    }
}