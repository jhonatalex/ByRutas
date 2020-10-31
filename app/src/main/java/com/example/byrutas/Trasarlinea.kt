package com.example.byrutas

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.byrutas.Tools.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class Trasarlinea : AppCompatActivity(),OnMapReadyCallback {

    lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trasarlinea)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap!= null) {
            mMap=googleMap
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }

        val uno=LatLng(-30.583444,-71.185667)
        val two=LatLng(-30.603326,-71.194257)
        val three=LatLng(-30.612819,-71.216488)
        val four=LatLng(-30.635080,-71.223946)
        val five=LatLng(-30.579156,-71.189903)





        mMap.addMarker(MarkerOptions().position(uno).title("PRUEBA"))
        mMap.mapType=GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isCompassEnabled=true
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uno,10f))

        /*mMap.addPolyline(PolylineOptions().add(uno)
                                          .add(two)
                                          .add(three)
                                          .add(four)
                                          .add(five)
                                          .width(8f)
                                          .color(Color.BLUE))
*/

        var points= mutableListOf<LatLng>()
        var lineOptions=PolylineOptions()


        for (i in 0 until Utils.routes.size) {
            Log.d("aqui", java.lang.String.valueOf(Utils.routes.size))

            val path: List<HashMap<String, String>> = Utils.routes[i]
            for (j in path.indices) {
                val point = path[j]
                val lat = point["lat"]!!.toDouble()
                val lng = point["lng"]!!.toDouble()
                val position = LatLng(lat, lng)
                points.add(position)
            }
            lineOptions.addAll(points)
            lineOptions.width(9F)
            lineOptions.color(Color.BLUE)

        }

        mMap.addPolyline(lineOptions)


    }


}