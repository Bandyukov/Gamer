package com.example.gamer.core.network.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    companion object {
        private const val BASE_URL = "https://api.rawg.io"
        private var apiFactory: ApiFactory? = null

        fun getInstance() : ApiFactory {
            var instance = apiFactory
            if (instance == null) {
                instance = ApiFactory()
                apiFactory = instance
            }
            return instance
        }

        private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun getApiService() : ApiService = retrofit.create(ApiService::class.java)
}