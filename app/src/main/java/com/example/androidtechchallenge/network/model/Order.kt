package com.example.androidtechchallenge.network.model

data class Order(
    val date: String,
    val marketName: String,
    val month: String,
    val orderName: String,
    val productDetail: ProductDetail,
    val productPrice: Double,
    val productState: String
)