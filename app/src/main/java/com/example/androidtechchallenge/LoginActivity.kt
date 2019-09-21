package com.example.androidtechchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidtechchallenge.app.UserManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkUserExist()

        btn_login.setOnClickListener {
            checkInputs(et_login_username.text.toString(), et_login_password.text.toString())
        }


    }

    fun checkInputs(username: String, password: String) {
        val isUsernameEmpty = username.isEmpty()
        val isPasswordEmpty = password.isEmpty()
        val isRememberMe = sw_login.isChecked
        val isUsernameCorrect = username.equals("kariyer")
        val isPasswordCorrect = password.equals("2019ADev")

        if (!isUsernameEmpty && !isPasswordEmpty)
            if (isUsernameCorrect && isPasswordCorrect) {
                login(username, password, isRememberMe)
                til_login_username.error = ""
            } else til_login_username.error = getString(R.string.error_validation)
        else
            til_login_username.error = if (isUsernameEmpty) getString(R.string.error_username) else ""
        til_login_password.error = if (isPasswordEmpty) getString(R.string.error_password) else ""
    }

    fun login(username: String, password: String, isRememberMe: Boolean) {
        val intent = Intent(this@LoginActivity, OrdersActivity::class.java)
        if (!isRememberMe) {
            UserManager.submitUser(username, password)
            startActivity(intent)
        } else
            startActivity(intent)
    }
    fun checkUserExist(){
        val intent = Intent(this@LoginActivity, OrdersActivity::class.java)
        UserManager.getUser()?.let {
            startActivity(intent)
        }
    }
}
