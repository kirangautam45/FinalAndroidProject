package com.example.finalproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.repository.BedroomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BedroomActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bedroom)
        recyclerView = findViewById(R.id.recyclerBedroom)
        loadBedsroom()
    }

    private fun loadBedsroom() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bedRoomRepository = BedroomRepository()
                val response = bedRoomRepository.getAllBedRoom()
                if (response.success == true) {
                    val lstbedroom = response.data
                    withContext(Main) {
                        recyclerView.adapter =
                                bedroomAdapter = (this@BedroomActivity, lstbedroom!!)


                    }
                }

            } catch (ex: Exception) {

            }
        }
    }
}