package com.example.finalproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapter.BedroomAdapter
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
                                BedroomAdapter(this@BedroomActivity, lstbedroom!!)
                        recyclerView.layoutManager =
                                LinearLayoutManager(this@BedroomActivity)


                    }
                }

            } catch (ex: Exception) {
                withContext(Main) {
                    Toast.makeText(
                            this@BedroomActivity,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }
}