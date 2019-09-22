package com.example.androidtechchallenge.app

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.androidtechchallenge.network.model.User
import com.google.gson.Gson

object UserManager {

    private const val USER = "USER"
    private const val NAME = "AndroidTechChallenge"
    private const val MODE = MODE_PRIVATE
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private var gson = Gson()

    // Application sınıfından türeyen App nesnesi ile uygualama başladığı zaman SharedPreferences ayarları yapılır.
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            NAME,
            MODE
        )
        editor = sharedPreferences.edit()
    }

    // Gelen kullanıcı adı ve şifre değerleri ile User nesnesi oluşturup,
    // Bu nesneyi JSON'a dönüştürüp ve SharedPreferences ' yazar.
    fun submitUser(username: String, password: String) {
        val json = gson.toJson(User(username, password))
        editor.putString(USER, json)
        editor.commit()
    }
    // SharedPreferences 'tan user JSON değerini çekip User nesnesine dönüştürür ve bu nesnesi dönüş değeri olarak return eder.
    fun getUser(): User? {
        val json = sharedPreferences.getString("USER", "")
        if (!json.isNullOrEmpty())
            return gson.fromJson(json, User::class.java)
        else
            return null
    }
    // SharedPreferences' tan USER değerini siler.
    fun deleteUser() {
        editor.remove("USER").commit()
    }
}