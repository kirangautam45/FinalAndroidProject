package com.example.finalproject.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.get
import com.example.finalproject.R
import com.example.finalproject.activity.LoginActivity
import com.example.finalproject.activity.MapsActivity
import com.example.finalproject.activity.OrderActivity
import com.example.finalproject.notification.NotificationChannels


class AboutusActivity : Fragment() {
    private lateinit var btnmap: Button
    private lateinit var logout: Button
    private lateinit var btnAddOrder: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_aboutus_activity, container, false)

        btnAddOrder=view.findViewById(R.id.btnAddOrder)
        btnmap=view.findViewById(R.id.btnmap)
        logout=view.findViewById(R.id.logout)

        logout.setOnClickListener {
            logout()

        }

        btnmap.setOnClickListener {

            startActivity(Intent(activity,MapsActivity::class.java))
        }

        btnAddOrder.setOnClickListener {
            startActivity(Intent(activity,OrderActivity::class.java))
        }


        return view
    }


    private fun logout() {
        val sharedpref =this.requireContext().getSharedPreferences("mypref", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedpref.edit()
        editor.clear()
        editor.apply()
        startActivity(Intent(activity,LoginActivity::class.java))
    }



}