package com.example.gamer.core.mapping

import com.example.gamer.core.entities.Game
import com.example.gamer.core.network.models.GameVO

fun GameVO.toGame() : Game = Game(id, title, released, posterPath, rating, topRating)