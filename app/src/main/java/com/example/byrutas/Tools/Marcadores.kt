package com.example.byrutas.Tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.example.byrutas.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

public data class Marcadores (
    val miMap:GoogleMap,
    val context: Context
){

    fun addMarkerDefault(){

        uno(-30.583444,-71.185667,"Punto 1")
        dos(-30.603326,-71.194257,"Punto 2")
        tres(-30.612819,-71.216488,"Punto 3")
        cuatro(-30.635080,-71.223946,"Punto 4")
        cinco(-30.579156,-71.189903,"Punto 5")

    }

    fun uno(latitude:Double,longitude:Double,title:String){

        val point:LatLng = LatLng(latitude,longitude)
        val width=165
        val height=140

        val markerOption= MarkerOptions().position(point).title(title)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        miMap.addMarker(markerOption)



    }

    fun dos(latitude:Double,longitude:Double,title:String){

        val point:LatLng = LatLng(latitude,longitude)
        val width=165
        val height=140

        val markerOption= MarkerOptions().position(point).title(title)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        miMap.addMarker(markerOption)


    }
    fun tres(latitude:Double,longitude:Double,title:String){

        val point:LatLng = LatLng(latitude,longitude)
        val width=165
        val height=140

        val markerOption= MarkerOptions().position(point).title(title)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        miMap.addMarker(markerOption)


    }
    fun cuatro(latitude:Double,longitude:Double,title:String){

        val point:LatLng = LatLng(latitude,longitude)
        val width=165
        val height=140

        val markerOption= MarkerOptions().position(point).title(title)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        miMap.addMarker(markerOption)


    }
    fun cinco(latitude:Double,longitude:Double,title:String){

        val point:LatLng = LatLng(latitude,longitude)
        val width=165
        val height=140

        val markerOption= MarkerOptions().position(point).title(title)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        miMap.addMarker(markerOption)


    }
}
