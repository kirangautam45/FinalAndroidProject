package com.example.finalproject.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.finalproject.R
import com.example.finalproject.activity.navigationdrawer.DashboardActivity
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.repository.UserRepository
import kotlinx.coroutines.*
import java.lang.Exception

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    var phone: String? = null
    var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!checkInternetConnection()) {
            Toast.makeText(
                    this,
                    "No Internet connection , please switch on the wifi or mobile data",
                    Toast.LENGTH_SHORT
            ).show()
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                getSharedPref()
                if (phone != "") {
                    login()
                } else {
                    loadLoginPage()
                }
            }
        }
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }



        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)

            startActivity(
                    Intent(this@SplashActivity,
                            LoginActivity::class.java
                    )
            )
            finish()
        }


    }

    private fun checkInternetConnection(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun loadLoginPage() {
        startActivity(
                Intent(
                        this@SplashActivity,
                        LoginActivity::class.java
                )
        )
        finish()
    }

    private fun login() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.loginUser(phone!!, password!!)
                if (response.success == true) {
                    // Save token
                    ServiceBuilder.token = "Bearer ${response.token}"
                    startActivity(
                            Intent(
                                    this@SplashActivity,
                                    DashboardActivity::class.java
                            )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        loadLoginPage()
                    }
                }
            } catch (ex: Exception) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@SplashActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }


    private fun getSharedPref() {
        val sharedPref = getSharedPreferences("UsernamePasswordPref", MODE_PRIVATE)
        phone = sharedPref.getString("phone", "")
        password = sharedPref.getString("password", "")
    }
}
