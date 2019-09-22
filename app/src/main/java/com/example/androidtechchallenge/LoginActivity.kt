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

        checkUserExist() // Önceden kaydedilmiş kullanıcı var mı diye kontrol eder.

        btn_login.setOnClickListener {
            checkInputs(et_login_username.text.toString(), et_login_password.text.toString())
        }
    }

    fun checkInputs(username: String, password: String) {

        // Login işleminden önce doldurulması gerekilen alanlar ile ilgili sorguları karşılayacak boolean verileri aldım.

        val isUsernameEmpty = username.isEmpty()
        val isPasswordEmpty = password.isEmpty()
        val isRememberMe = sw_login.isChecked
        val isUsernameCorrect = username.equals("kariyer")
        val isPasswordCorrect = password.equals("2019ADev")

        // Eğer input alanlarından eksik olursa o alanlar için özel hata mesajı,
        // Kullanıcı adı ve şifre kombinasyonu hatalı ise farklı bir hata mesajı verdim.
        // Hata mesajını vermek için TextInputLayout nesnesinden türeyen CustomTextInputLayout sınıfı kullandım.
        // Bu sınıfın tek farkı editText in background değerini değiştirmemesidir.
        // Bu sorgular sonucu girilen input değerleri doğru ise login metodu çalışır.

        if (!isUsernameEmpty && !isPasswordEmpty)
            if (isUsernameCorrect && isPasswordCorrect) {
                login(username, password, isRememberMe)
                til_login_username.error = ""
            } else til_login_username.error = getString(R.string.error_validation)
        else
            til_login_username.error = if (isUsernameEmpty) getString(R.string.error_username) else ""
        til_login_password.error = if (isPasswordEmpty) getString(R.string.error_password) else ""
    }


    // isRememberMe login ekranındaki switch'in enable/disable olayını tutar.
    // isRememberMe true ise kullanıcı adı ve şifreyi bir User nesnesi haline getirip,
    // UserManager objesi ile SharedPreferences' e kaydeder.
    // UserManger objesi SharedPreferences ile ilgili işlemleri yapması için oluşturduğum nesnedir.
    // Beni hatırla seçeneğine göre yapılan login işlemi sonucu intent ile Siparişlerim sayfasına gidilir.

    fun login(username: String, password: String, isRememberMe: Boolean) {
        val intent = Intent(this@LoginActivity, OrdersActivity::class.java)
        if (isRememberMe) {
            UserManager.submitUser(username, password)
            startActivity(intent)
            finish()
        } else
            startActivity(intent)
        finish()
    }

    // UserManager objesi ile SharedPreferences'te kayıt var mı diye sorguladım.
    // Kayıt bulunursa intent ile Siparişlerim sayfasına gidilir.
    fun checkUserExist() {
        val intent = Intent(this@LoginActivity, OrdersActivity::class.java)
        UserManager.getUser()?.let {
            startActivity(intent)
            finish()
        }
    }
}
