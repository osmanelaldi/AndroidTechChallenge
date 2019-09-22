package com.example.androidtechchallenge.network

import com.example.androidtechchallenge.network.model.Order
import retrofit2.Call
import retrofit2.http.GET

interface IDataService {

    @GET(" ")
    fun getOrders() : Call<List<Order>>
}