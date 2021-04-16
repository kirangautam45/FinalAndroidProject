package com.example.finalproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.finalproject.R
import com.example.finalproject.fragments.AboutusActivity
import com.example.finalproject.fragments.DashboardActivity
import com.example.finalproject.fragments.ProductAddActivity

class DashActivity : AppCompatActivity() {
    private lateinit var dashboard:Button
    private lateinit var productadd:Button
    private lateinit var aboutus:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
        dashboard=findViewById(R.id.dashboard)
        productadd=findViewById(R.id.productadd)
        aboutus=findViewById(R.id.aboutus)

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
}