package com.example.gamer.core.network.models

import com.google.gson.annotations.SerializedName

data class GameVO(
    @SerializedName(value = "id")
    val id: Int,

    @SerializedName(value = "name")
    val title: String,

    @SerializedName(value = "released")
    val released: String,

    @SerializedName(value = "background_image")
    val posterPath: String?,

    @SerializedName(value = "rating")
    val rating: Double,

    @SerializedName(value = "rating_top")
    val topRating: Int
)