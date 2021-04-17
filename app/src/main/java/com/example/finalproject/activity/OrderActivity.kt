package com.example.finalproject.activity

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.finalproject.R
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.entity.Order
import com.example.finalproject.entity.User
import com.example.finalproject.repository.OrderRepository
import com.example.finalproject.repository.UserRepository
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class OrderActivity : AppCompatActivity(),SensorEventListener{
    private lateinit var name: EditText
    private lateinit var material: EditText
    private lateinit var cost: EditText
    private lateinit var feature: EditText
    private lateinit var btnOrder: Button
    private var sensorManager: SensorManager?=null
    private var sensor: Sensor?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        name = findViewById(R.id.name)
        material = findViewById(R.id.material)
        cost = findViewById(R.id.cost)
        feature = findViewById(R.id.featutre)
        btnOrder = findViewById(R.id.btnOrder)

        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)



        btnOrder.setOnClickListener {

            val name = name.text.toString()
            val cost = cost.text.toString()
            val feature = featutre.text.toString()
            val material = material.text.toString()


            val order = Order(
                name=name,
                cost=cost,
                material = material,
            feature=feature
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = OrderRepository()
                    val response = repository.insetrOrder(order)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@OrderActivity,
                                "  order request to admin wait for respond", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@OrderActivity,
                            "error to send order", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
    var isRunning=false
    override fun onSensorChanged(event: SensorEvent?) {
        try {
            if (event!!.values[0] <20 && !isRunning){
                isRunning=true
                window.decorView.setBackgroundColor(Color.YELLOW)
            }else{isRunning = false
                window.decorView.setBackgroundColor(Color.BLUE)
            }
        }catch (e:IOException){}
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

}