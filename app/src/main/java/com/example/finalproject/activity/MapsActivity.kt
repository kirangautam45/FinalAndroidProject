package com.example.finalproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        mMap.addMarker(
            MarkerOptions().position(LatLng(27.6976927,85.3297752))
                .title("our furniture product")
                .icon(
                    BitmapDescriptorFactory.defaultMarker
                    (BitmapDescriptorFactory.HUE_BLUE)
                )
        )
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom
                (LatLng(27.6976927,85.3297752), 16F), 4000, null
        )
        mMap.uiSettings.isZoomControlsEnabled=true
    }
}