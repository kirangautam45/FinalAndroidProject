package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalproject.db.UserDB
import com.example.finalproject.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class loginActivity : AppCompatActivity() {
    private lateinit var phone: EditText
    private lateinit var Password: EditText
    private lateinit var btnlogin: Button
    private lateinit var register: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        phone = findViewById(R.id.Phone)
        Password = findViewById(R.id.Password)
        btnlogin = findViewById(R.id.btnLogin)
        register = findViewById(R.id.register)
        phone.setText("")
        Password.setText("")
        btnlogin.setOnClickListener {
            saveSharePref()
            login()
        }
        register.setOnClickListener {
            getSharedPref()
            startActivity(
                Intent(
                    this@loginActivity,
                    RegistrationActivity::class.java
                )
            )
        }

    }
    private fun saveSharePref() {
        val phone = phone.text.toString()
        val Password = Password.text.toString()
        val sharePref = getSharedPreferences("kiranPref", MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putString("phone", phone)
        editor.putString("password", Password)
        editor.apply()
        Toast.makeText(this@loginActivity, "Username and Password saved", Toast.LENGTH_SHORT).show()

    }

    private fun getSharedPref() {
        val sharePref = getSharedPreferences("kiranPref", MODE_PRIVATE)
        val phone = sharePref.getString("phone", "")
        val Password = sharePref.getString("password", "")
        Toast.makeText(this, "Phone :$phone and password :$Password", Toast.LENGTH_SHORT)
            .show()
    }

    private fun login() {
        val phone = phone.text.toString()
        val U_password = Password.text.toString()

        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = UserDB.getInstance(this@loginActivity)
                .getUserDAO().checkUser(phone, U_password)

            if (user == null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@loginActivity, "Invalid credentials",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

            } else {
                val intent = (Intent(this@loginActivity, DashboardActivity::class.java))
                startActivity(intent)
            }
        }
    }
    }