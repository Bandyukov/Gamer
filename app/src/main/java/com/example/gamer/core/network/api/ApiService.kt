package com.example.gamer.core.network.api

import com.example.gamer.core.network.models.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("/api/games")
    fun getData(@QueryMap params: Map<String, String>) : Observable<Response>
}