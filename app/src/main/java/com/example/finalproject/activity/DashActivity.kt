package com.example.finalproject.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.fragments.AboutusActivity
import com.example.finalproject.fragments.DashboardActivity
import com.example.finalproject.fragments.ProductAddActivity

class DashActivity : AppCompatActivity(),SensorEventListener {

    private lateinit var dashboard:Button
    private lateinit var productadd:Button
    private lateinit var aboutus:Button
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
        dashboard=findViewById(R.id.dashboard)
        productadd=findViewById(R.id.productadd)
        aboutus=findViewById(R.id.aboutus)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        dashboard.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer,DashboardActivity())
                addToBackStack(null)
                commit()
            }
        }
        productadd.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer,ProductAddActivity())
                addToBackStack(null)
                commit()
            }
        }
        aboutus.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.linearcontainer,AboutusActivity())
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values
        if (event.values[1] > 0 && event.values[1] <= 20) {
            val fragment: Fragment = ProductAddActivity()
            supportFragmentManager.beginTransaction().replace(R.id.product,
                    fragment).commit()
            Toast.makeText(this@DashActivity, "Dashboard", Toast.LENGTH_SHORT).show()
        } else if (event.values[1] < 0) {
            val fragment2: Fragment = AboutusActivity()
            supportFragmentManager.beginTransaction().replace(R.id.aboutus,
                    fragment2).commit()
            Toast.makeText(this@DashActivity, "Dashboard", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}