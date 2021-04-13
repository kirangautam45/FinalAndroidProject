package com.example.finalproject.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.finalproject.R
import com.example.finalproject.activity.MapsActivity


class AboutusActivity : Fragment() {
    private lateinit var btnmap:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_aboutus_activity, container, false)
        btnmap=view.findViewById(R.id.btnmap)

        btnmap.setOnClickListener {
startActivity(Intent(activity,MapsActivity::class.java))
        }
        return view
    }


}