package com.example.gamer.core.entities

import com.example.gamer.core.base.ListItem

data class Game(
    val id: Int,
    val title: String,
    val released: String,
    val posterPath: String?,
    val rating: Double,
    val topRating: Int,
) : ListItem{
    override val itemId: Int
        get() = id
}