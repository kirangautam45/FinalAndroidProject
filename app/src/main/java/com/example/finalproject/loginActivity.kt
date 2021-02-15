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
    private lateinit var Username: EditText
    private lateinit var Password: EditText
    private lateinit var btnlogin: Button
    private lateinit var register: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Username = findViewById(R.id.Username)
        Password = findViewById(R.id.Password)
        btnlogin = findViewById(R.id.btnLogin)
        register = findViewById(R.id.register)
        Username.setText("")
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
                    SplashActivity::class.java
                )
            )
        }

    }
    private fun saveSharePref() {
        val Username = Username.text.toString()
        val Password = Password.text.toString()
        val sharePref = getSharedPreferences("kiranPref", MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putString("username", Username)
        editor.putString("password", Password)
        editor.apply()
        Toast.makeText(this@loginActivity, "Username and Password saved", Toast.LENGTH_SHORT).show()

    }

    private fun getSharedPref() {
        val sharePref = getSharedPreferences("kiranPref", MODE_PRIVATE)
        val Username = sharePref.getString("username", "")
        val Password = sharePref.getString("password", "")
        Toast.makeText(this, "Username :$Username and password :$Password", Toast.LENGTH_SHORT)
            .show()
    }

    private fun login() {
        val U_name = Username.text.toString()
        val U_password = Password.text.toString()

        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = UserDB.getInstance(this@loginActivity)
                .getUserDAO().checkUser(U_name, U_password)

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