package com.example.androidtechchallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtechchallenge.R
import com.example.androidtechchallenge.network.model.Order
import kotlinx.android.synthetic.main.item_order.view.*

class OrdersAdapter(var orders : List<Order>) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false))
    }
    override fun getItemCount() = orders.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.itemView.tv_order_day.text = order.date
        holder.itemView.tv_order_month.text = order.month
        holder.itemView.tv_order_marketName.text = order.marketName
        holder.itemView.tv_order_name.text = order.orderName
        holder.itemView.tv_order_price.text = order.productPrice.toString() + "TL"
    }

    class OrderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}