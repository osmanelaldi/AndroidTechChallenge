package com.example.androidtechchallenge.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtechchallenge.R
import com.example.androidtechchallenge.network.model.Order
import kotlinx.android.synthetic.main.item_order.view.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat

class OrdersAdapter(var orders: List<Order>) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {
    lateinit var parent: ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        this.parent = parent
        return OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))
    }

    override fun getItemCount() = orders.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        val isVisible = if (holder.isExpanded) View.VISIBLE else View.GONE // Ayrıntıların görünmesini kontrol eder.
        val state = order.productState

        // Siparişin hangi aşamada olduğuna göre sipariş durumu rengini ayarlar.
        if(state.equals("Yolda")) {
            holder.itemView.iv_state.setImageResource(R.color.appThemeGreen)
            holder.itemView.tv_order_state.setTextColor(parent.context.resources.getColor(R.color.appThemeGreen))
        }
        else if (state.equals("Hazırlanıyor")) {
            holder.itemView.iv_state.setImageResource(R.color.appThemeOrange)
            holder.itemView.tv_order_state.setTextColor(parent.context.resources.getColor(R.color.appThemeOrange))
        }
        else if (state.equals("Onay Bekliyor")){
            holder.itemView.iv_state.setImageResource(R.color.appThemeRed)
            holder.itemView.tv_order_state.setTextColor(parent.context.resources.getColor(R.color.appThemeRed))
        } else {}


        // Ayrıntıların görünürlüğünü view'e verdiğimiz atamalar.
        holder.itemView.tv_order_product_detail.visibility = isVisible
        holder.itemView.tv_order_product_detailPrice.visibility = isVisible
        holder.itemView.view_visible.visibility = isVisible


        // Api den gelen her bir Order nesnesi değerini view'e atamalar ile sorumlu olan kodlar.
        holder.itemView.tv_order_day.text = order.date
        holder.itemView.tv_order_month.text = DateFormatSymbols.getInstance().months.get(order.month.toInt() - 1)
        holder.itemView.tv_order_marketName.text = order.marketName
        holder.itemView.tv_order_name.text = order.orderName
        holder.itemView.tv_order_price.text = order.productPrice.toString() + " TL"
        holder.itemView.tv_order_state.text = order.productState
        holder.itemView.tv_order_product_detail.text = order.productDetail.orderDetail
        holder.itemView.tv_order_product_detailPrice.text = order.productDetail.summaryPrice.toString() + " TL"


        // Item'a tıklandığı zaman görünümü değiştirir.TransitionManager ile animasyon elde edilir.Recyclerview notifiy edilir.
        holder.itemView.setOnClickListener {
            TransitionManager.beginDelayedTransition(this.parent)
            holder.isExpanded = !holder.isExpanded
            notifyItemChanged(position)
        }



    }
    // Her recyclerview iteminin görünümü ilk zamanda false yapıyorum.
    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var isExpanded: Boolean

        init {
            isExpanded = false
        }
    }


}