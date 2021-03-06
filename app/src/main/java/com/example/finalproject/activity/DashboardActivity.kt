package com.example.finalproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.fragment.AboutFragment
import com.example.finalproject.fragment.HomeFragment
import com.example.finalproject.fragment.ProductFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val homeFragment=HomeFragment()
    private val aboutFragment=AboutFragment()
    private val productFragment=ProductFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        replaceFragment(homeFragment)


        button_Navigation.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.Home -> replaceFragment(homeFragment)
                R.id.Product -> replaceFragment(productFragment)
                R.id.About -> replaceFragment(aboutFragment)
            }
            true

        }

    }
    private fun replaceFragment(fragment :Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.navigation_container,fragment)
            transaction.commit()
        }
    }
}