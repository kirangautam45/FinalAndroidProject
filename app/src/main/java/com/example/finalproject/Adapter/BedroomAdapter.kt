package com.example.finalproject.Adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.api.ServiceBuilder
import com.example.finalproject.entity.Bedroom
import kotlinx.android.synthetic.main.custom_bedroom_layout.view.*
import org.w3c.dom.Text

class BedroomAdapter(
        private val context: Context,
        private val lstbedroom: MutableList<Bedroom>
) : RecyclerView.Adapter<BedroomAdapter.BedroomViewHolder>() {
    class BedroomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imgbed)
        val bedname: TextView = view.findViewById(R.id.bedname)
        val size: TextView = view.findViewById(R.id.size)
        val cost: TextView = view.findViewById(R.id.cost)
        val describe: TextView = view.findViewById(R.id.describe)
        val material: TextView = view.findViewById(R.id.material)
        val btnaddcard: ImageView = view.findViewById(R.id.addcard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BedroomViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_bedroom_layout, parent, false)
        return BedroomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BedroomViewHolder, position: Int) {
        val bedroom = lstbedroom[position]
        holder.bedname.text = bedroom.bedname
        holder.cost.text = bedroom.cost
        holder.describe.text = bedroom.describe
        holder.size.text = bedroom.size
        holder.describe.text = bedroom.describe
       // val image = "{${ServiceBuilder.loadImagePath()}" + { bedroom.image }

    }

    override fun getItemCount(): Int {
        return lstbedroom.size
    }
}
