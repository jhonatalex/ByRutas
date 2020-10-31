package com.example.byrutas.Tools

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat


public class GPS_controler:LocationListener {
    var context: Context? = null

    fun GPS_controler(context: Context?) {

        this.context = context
    }

    fun getLocation(): Location? {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("fist", "error")
            return null
        }
        try {
            val lm = context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (isGPSEnabled) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this)
                return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            } else {
                Log.e("sec", "error")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}



    override fun onLocationChanged(p0: Location) {

    }
}