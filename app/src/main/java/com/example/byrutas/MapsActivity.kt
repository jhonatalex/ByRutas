package com.example.byrutas

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private lateinit var myMap: GoogleMap

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var lastLocation: Location


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE=1

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this)


        ir.setOnClickListener {
            val intento1 = Intent(this, RutasActivity::class.java)
            startActivity(intento1)

        }






    }

    //INSTANTIATE EL CLICK EN EL MAIN
    override fun onMarkerClick(p0: Marker?)=false

    //CHANGE COLOR THE POINTER
    private fun placeMarker(location:LatLng){
        val markerOption=MarkerOptions().position(location)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        myMap.addMarker(markerOption)

    }


    //METHOD STAR DE MAPS
    override fun onMapReady(googleMap: GoogleMap) {

        myMap = googleMap


        myMap.setOnMarkerClickListener(this)
        myMap.uiSettings.isCompassEnabled=true
        myMap.uiSettings.isZoomControlsEnabled=true

        setMap()


    }



    //Methods PUT DE MAPS
    private fun setMap() {
        // SOLICITUD EN TIEMPO REAL DE PERSMISOS
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        myMap.isMyLocationEnabled = true
        myMap.mapType=GoogleMap.MAP_TYPE_HYBRID  // CAMBIA EL TIPO DE MAPA


        fusedLocationClient.lastLocation.addOnSuccessListener (this) { location->

            if (location!=null){
                lastLocation=location
                val currentLatLong=LatLng(location.latitude,location.longitude)
                placeMarker(currentLatLong)
                myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,18f))

            }
        }

    }









}