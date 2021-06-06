package com.example.gamer.core.network.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName(value = "next")
    val nextPage: String,

    @SerializedName(value = "results")
    val results: List<GameVO>
)