package com.example.finalproject.activity

import android.app.NotificationChannel
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.finalproject.R
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.entity.User
import com.example.finalproject.notification.NotificationChannels
import com.example.finalproject.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationActivity : AppCompatActivity() {
    private lateinit var Fname : EditText
    private lateinit var Lname: EditText
    private lateinit var Address: EditText
    private lateinit var Phone: EditText
    private lateinit var Password: EditText
    private lateinit var ConfirmPassword: EditText
    private lateinit var btnAdduser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        Fname=findViewById(R.id.Fname)
        Lname=findViewById(R.id.Lname)
        Address=findViewById(R.id.Address)
        Phone=findViewById(R.id.Phone)
        Password=findViewById(R.id.Password)
        ConfirmPassword=findViewById(R.id.ConfirmPassword)
        btnAdduser=findViewById(R.id.btnAdduser)



        btnAdduser.setOnClickListener {
            showLowPriorityNotification()
            val fname= Fname.text.toString()
            val lname=Lname.text.toString()
            val address = Address.text.toString()
            val phone = Phone.text.toString()
            val password =Password.text.toString()
            val confirmpassword = ConfirmPassword.text.toString()

            if(password != confirmpassword){
                Password.error= "password does not match"
                Password.requestFocus()
                return@setOnClickListener }
            else {
                val user= User(fname=fname,lname=lname,address=address,phone=phone,password=password)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val repository = UserRepository()
                        val response = repository.registerUser(user)
                        if (response.success==true){
                            ServiceBuilder.token="Bearer "+response.token
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@RegistrationActivity,
                                    "user  added", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@RegistrationActivity,
                                "Username cannot be duplicate", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }

    }
    private fun showLowPriorityNotification() {
        val notificationManager = NotificationManagerCompat.from(this)


        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_2)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Low priority notification")
                .setContentText("user has been successfully added")
                .setColor(Color.BLUE)
                .build()

        notificationManager.notify(2, notification)
    }
}