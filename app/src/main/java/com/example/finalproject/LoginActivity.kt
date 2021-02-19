package com.example.finalproject

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.example.finalproject.api.ServiceBuilder

import com.example.finalproject.repository.UserRepository
import com.google.android.material.snackbar.Snackbar

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var phone: EditText
    private lateinit var password: EditText
    private lateinit var btnlogin: Button
    private lateinit var chkRememberMe: CheckBox
    private lateinit var register: TextView
    private lateinit var linearLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        phone = findViewById(R.id.Phone)
        password = findViewById(R.id.Password)
        btnlogin = findViewById(R.id.btnLogin)
        register = findViewById(R.id.register)
        chkRememberMe = findViewById(R.id.chkRememberMe)
        linearLayout = findViewById(R.id.linearLayout)
        checkRunTimePermission()

        btnlogin.setOnClickListener {
//           saveSharePref()
            login()
        }
        register.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegistrationActivity::class.java
                )
            )
        }

    }

    private fun checkRunTimePermission() {
        if (!hasPermission()) {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@LoginActivity, permissions, 1)

    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }


    private fun login(){
        val phone = phone.text.toString()
        val password = password.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response= repository.loginUser(phone,password)
                if(response.success ==true){
                    ServiceBuilder.token ="Bearer " + response.token
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            DashboardActivity::class.java
                        )
                    )
                    finish()
                } else{
                    withContext(Dispatchers.Main){
                        val snack =
                            Snackbar.make(
                                linearLayout,"Invalid Credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("ok", View.OnClickListener {
                            snack.dismiss()
                        })
                    snack.show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}


//    private fun saveSharePref() {
//        val phone = phone.text.toString()
//        val Password = Password.text.toString()
//        val sharePref = getSharedPreferences("kiranPref", MODE_PRIVATE)
//        val editor = sharePref.edit()
//        editor.putString("phone", phone)
//        editor.putString("password", Password)
//        editor.apply()
//        Toast.makeText(this@loginActivity, "Username and Password saved", Toast.LENGTH_SHORT).show()
//
//    }
//
//    private fun getSharedPref() {
//        val sharePref = getSharedPreferences("kiranPref", MODE_PRIVATE)
//        val phone = sharePref.getString("phone", "")
//        val Password = sharePref.getString("password", "")
//        Toast.makeText(this, "Phone :$phone and password :$Password", Toast.LENGTH_SHORT)
//            .show()
//    }

//    private fun login() {
//        val phone = phone.text.toString()
//        val U_password = Password.text.toString()
//
//        var user: User? = null
//        CoroutineScope(Dispatchers.IO).launch {
//            user = UserDB.getInstance(this@loginActivity)
//                .getUserDAO().checkUser(phone, U_password)
//
//            if (user == null) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        this@loginActivity, "Invalid credentials",
//                        Toast.LENGTH_SHORT
//                    )
//                        .show()
//                }
//
//            } else {
//                val intent = (Intent(this@loginActivity, DashboardActivity::class.java))
//                startActivity(intent)
//            }
//        }
//    }
    //}