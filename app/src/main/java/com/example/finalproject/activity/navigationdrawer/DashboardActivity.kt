package com.example.finalproject.activity.navigationdrawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.activity.BedroomActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    private var items = arrayListOf(
            NavigationitemModel(R.drawable.about, "User Details"),
            NavigationitemModel(R.drawable.bed, "Bedroom"),
            NavigationitemModel(R.drawable.coomingsoon, "Coming Product"),
            NavigationitemModel(R.drawable.map, "Location")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        drawerLayout = findViewById(R.id.drawer_layout)

        //set the toolbar
        setSupportActionBar(main_toolbar)

        // setup Recyclerview's Layout
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)

//        navigation_rv.addOnItemTouchListener(
//                RecyclerTouchListener(
//                        this,
//                        object : ClickListener {
//                            override fun onClick(view: View, position: Int) {
//                                when (position) {
//                                        0->{
//                                            val intent = Intent(this@DashboardActivity, BedroomActivity::class.java)
//                                        }
//                                }
//
//                            }
//
//
//                        }

    }
}



