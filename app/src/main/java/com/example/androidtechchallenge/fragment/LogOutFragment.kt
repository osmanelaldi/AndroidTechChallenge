package com.example.androidtechchallenge.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.example.androidtechchallenge.R
import kotlinx.android.synthetic.main.fragment_logout.view.*

class LogOutFragment(val listener : LogOutListener) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_logout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.btn_logout_fragment_cancel.setOnClickListener { listener.isLogOut(false) }
        view.btn_logout_fragment_confirm.setOnClickListener { listener.isLogOut(true) }
    }

    // OrdersActivity e gönderdiğim fonksiyon. Aldığı boolean değerine göre çıkış yapar ya da vazgeçer.
    interface LogOutListener{
        fun isLogOut(logOut : Boolean)
    }

}