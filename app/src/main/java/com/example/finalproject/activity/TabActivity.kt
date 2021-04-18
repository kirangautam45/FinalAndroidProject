package com.example.finalproject.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.finalproject.Adapter.ViewPagerAdapter
import com.example.finalproject.R
import com.example.finalproject.fragments.AboutusActivity
import com.example.finalproject.fragments.DashboardActivity
import com.example.finalproject.fragments.ProductAddActivity
import com.example.finalproject.notification.NotificationChannels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.io.IOException

class TabActivity : AppCompatActivity(),SensorEventListener {
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )
    private lateinit var lstTitle:ArrayList<String>
    private lateinit var lstFragments:ArrayList<Fragment>
    private lateinit var viewpager:ViewPager2
    private lateinit var tabLayout: TabLayout
    private var sensorManager: SensorManager?=null
    private var sensor: Sensor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        welcome()

        if (!hasPermission()){
            requestPermission()
        }
        viewpager=findViewById(R.id.viewpager)
        tabLayout=findViewById(R.id.tablayout)

        productlist()
        val adapter = ViewPagerAdapter(lstFragments,supportFragmentManager,lifecycle)
        viewpager.adapter = adapter
        TabLayoutMediator(tabLayout,viewpager){tab,position ->
            tab.text=lstTitle[position]
        }.attach()
    }

    private fun welcome() {
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("User login")
                .setContentText("user has been successfully login")
                .setColor(Color.BLUE)
                .build()

        notificationManager.notify(1, notification)
    }

    private fun productlist() {
        lstTitle= ArrayList<String>()
        lstTitle.add("Dashboard")
        lstTitle.add("Product")
        lstTitle.add("About US")
        lstFragments= ArrayList<Fragment>()
        lstFragments.add(DashboardActivity())
        lstFragments.add(ProductAddActivity())
        lstFragments.add(AboutusActivity())
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@TabActivity,
            permissions,1
        )
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions){
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
            ) != PackageManager.PERMISSION_GRANTED)
            {
                hasPermission=false
            }
        }
        return hasPermission

    }
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]
        if (values <= 4) {
            try {
                logoutapp()
            } catch (e: IOException) {
            }
        }
    }

    private fun logoutapp() {
        val sharedpref =getSharedPreferences("mypref", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedpref.edit()
        editor.clear()
        editor.apply()
        startActivity(Intent(this@TabActivity,LoginActivity::class.java))
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


}