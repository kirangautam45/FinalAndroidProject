package com.example.finalproject.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.finalproject.R
import com.example.finalproject.activity.LoginActivity
import com.example.finalproject.activity.MapsActivity


class AboutusActivity : Fragment() {
    private lateinit var btnmap:Button
    private lateinit var logout: Button
    private lateinit var webSite: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_aboutus_activity, container, false)

        webSite=view.findViewById(R.id.webSite)
        webSite.loadUrl("https://pierowayfurniture.com/bedroom/")
        val webSettings = webSite.getSettings()
        webSettings.setJavaScriptEnabled(true)
        btnmap=view.findViewById(R.id.btnmap)



        logout=view.findViewById(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        btnmap.setOnClickListener {
startActivity(Intent(activity,MapsActivity::class.java))
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