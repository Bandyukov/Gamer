package com.example.gamer.ui.presenters

import com.example.gamer.core.entities.Game

interface GameListView {
    fun showData(games: List<Game>)
    fun showError(throwable: Throwable)
}