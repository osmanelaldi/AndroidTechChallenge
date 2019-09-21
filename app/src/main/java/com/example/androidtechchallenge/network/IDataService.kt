package com.example.androidtechchallenge.network

import com.example.androidtechchallenge.network.model.OrdersResponse
import retrofit2.Call
import retrofit2.http.GET

interface IDataService {

    @GET
    fun getOrders() : Call<OrdersResponse>
}