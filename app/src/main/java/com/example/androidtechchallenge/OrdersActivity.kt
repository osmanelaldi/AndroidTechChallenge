package com.example.androidtechchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidtechchallenge.adapters.OrdersAdapter
import com.example.androidtechchallenge.app.UserManager
import com.example.androidtechchallenge.fragment.LogOutFragment
import com.example.androidtechchallenge.network.Repository
import com.example.androidtechchallenge.network.model.Order
import kotlinx.android.synthetic.main.activity_orders.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersActivity : AppCompatActivity(), LogOutFragment.LogOutListener {
    lateinit var logOutFragment: LogOutFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        rv_orders.itemAnimator = null

        // Adresten verileri çekmek için Retrofit Call kullandım.
        // Retrofit ile request yapabilmek için üç yapı kullandım. Api,Repository objesi ve IDataService interface i
        // Api objesinde , requesti yapabilmesi için Retrofit nesnesi create edilir.
        // IDataService'te @GET metodları ve dönüş tiplerini belirledim.
        // Reposityory objesi IDataService' teki metodları implement eden sınıftır.

        Repository.getOrders().enqueue(object : Callback<List<Order>> {

            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                response.body()?.let { ordersList ->

                    // Request sonucu null olmayan veri var ise bu veri ile recyclerview'in apadter'ini oluşturdum.
                    // Daha sonra loading için kullanıdığım viewleri görünmez yaptım.

                    rv_orders.adapter = OrdersAdapter(ordersList)
                    pb_order.visibility = View.GONE
                    tv_order_loading.visibility = View.GONE
                }
            }
            override fun onFailure(call: Call<List<Order>>, t: Throwable) {}
        })

        btn_logout.setOnClickListener {
            // Çıkış butonuna basıldığı zaman uyarı vermesi için DialogFragment kullandım.
            logOutFragment = LogOutFragment(this)
            logOutFragment.show(supportFragmentManager, "logOutFragment")
        }
    }

    override fun isLogOut(logOut: Boolean) {

        // Çıkış işlemi için gelen DialogFragment ekranındaki Interface ile tetiklenen bir metod.
        // Ekranda çıkış işlemi kabul edilirse bu metod çalışır ve UserManager ile kullanıcıyı silip Login ekranına döner.

        if (logOut) {
            UserManager.deleteUser()
            logOutFragment.dismiss()
            val intent = Intent(this@OrdersActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            logOutFragment.dismiss()
        }
    }
}
