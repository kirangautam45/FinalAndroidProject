package com.example.furniturewear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class LoginActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Enables Always-on
        setAmbientEnabled()
    }
}