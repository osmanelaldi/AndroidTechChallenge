package com.example.androidtechchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidtechchallenge.adapters.OrdersAdapter
import com.example.androidtechchallenge.network.Repository
import com.example.androidtechchallenge.network.model.Order
import kotlinx.android.synthetic.main.activity_orders.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        Repository.getOrders().enqueue(object : Callback<List<Order>>{

            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                response.body()?.let { ordersList->
                    rv_orders.adapter = OrdersAdapter(ordersList)
                }
            }
            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }
}
