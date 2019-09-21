package com.example.androidtechchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidtechchallenge.adapters.OrdersAdapter
import com.example.androidtechchallenge.network.Repository
import com.example.androidtechchallenge.network.model.OrdersResponse
import kotlinx.android.synthetic.main.activity_orders.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        Repository.getOrders().enqueue(object : Callback<OrdersResponse>{
            override fun onResponse(call: Call<OrdersResponse>, response: Response<OrdersResponse>) {
                response.body()?.let {ordersResponse ->
                    ordersResponse.orders.isNotEmpty().let {
                        rv_orders.adapter = OrdersAdapter(ordersResponse.orders)
                    }
                }
            }
            override fun onFailure(call: Call<OrdersResponse>, t: Throwable) {
            }
        })

    }
}
