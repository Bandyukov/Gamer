package com.example.gamer.core.network.models

class Params(
    private val dates: String,
    private val ordering: String,
) {
    private val key = "a9e3e19107ac415e89612f246ae17699"

    fun toMap(): Map<String, String> = mutableMapOf<String, String>().apply {
        put("key", key)
        put("date", dates)
        put("ordering", ordering)
    }

}