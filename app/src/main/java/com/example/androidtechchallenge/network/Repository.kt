package com.example.androidtechchallenge.network

object Repository {

    fun getOrders() = Api.client.getOrders()
}