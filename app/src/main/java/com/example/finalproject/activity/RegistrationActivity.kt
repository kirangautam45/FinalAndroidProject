package com.example.finalproject.activity

import android.app.NotificationChannel
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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

class RegistrationActivity : AppCompatActivity(),SensorEventListener {
    private lateinit var Fname : EditText
    private lateinit var Lname: EditText
    private lateinit var Address: EditText
    private lateinit var Phone: EditText
    private lateinit var Password: EditText
    private lateinit var ConfirmPassword: EditText
    private lateinit var btnAdduser: Button
    private var sensorManager: SensorManager?=null
    private var sensor: Sensor?=null
    private var mAccel = 0f
    private var mAccelCurrent = 0f
    private var mAccelLast = 0f

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

        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;



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

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        val x: Float = event!!.values[0]
        val y: Float = event!!.values[1]
        val z: Float = event!!.values[2]
        mAccelLast = mAccelCurrent
        mAccelCurrent = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val delta: Float = mAccelCurrent - mAccelLast
        mAccel = mAccel * 0.9f + delta
        if (mAccel > 12) {
            loginactivity()
        }
    }
    private fun loginactivity() {
        val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
        startActivity(intent)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}