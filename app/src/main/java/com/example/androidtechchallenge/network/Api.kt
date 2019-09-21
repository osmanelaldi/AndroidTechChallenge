package com.example.androidtechchallenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Api {
    const val BASE_URL = "http://kariyertechchallenge.mockable.io/"
    val client : IDataService
    get() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IDataService::class.java)
    }

}