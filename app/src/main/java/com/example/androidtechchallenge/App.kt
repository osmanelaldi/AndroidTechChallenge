package com.example.androidtechchallenge

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        UserManager.init(this)
    }
}