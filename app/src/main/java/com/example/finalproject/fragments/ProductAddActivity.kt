package com.example.finalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapter.BedroomAdapter
import com.example.finalproject.R
import com.example.finalproject.repository.BedroomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ProductAddActivity : Fragment() {
    private lateinit var recyclerview:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_add_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview=view.findViewById(R.id.recyclerview)

        loadproduct()
    }

    private fun loadproduct() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bedroomRepository = BedroomRepository()
                val response = bedroomRepository.getAllBedRoom()
                if (response.data !=null){
                    val lstproduct = response.data
                    withContext(Dispatchers.Main){
                        recyclerview.layoutManager=LinearLayoutManager(activity)
                        recyclerview.adapter = BedroomAdapter(requireContext(),lstproduct)
                    }
                }
            } catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}