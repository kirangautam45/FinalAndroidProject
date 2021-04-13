package com.example.finalproject.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.finalproject.Adapter.ViewPagerAdapter
import com.example.finalproject.R
import com.example.finalproject.fragments.AboutusActivity
import com.example.finalproject.fragments.DashboardActivity
import com.example.finalproject.fragments.ProductAddActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )
    private lateinit var lstTitle:ArrayList<String>
    private lateinit var lstFragments:ArrayList<Fragment>
    private lateinit var viewpager:ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

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
}