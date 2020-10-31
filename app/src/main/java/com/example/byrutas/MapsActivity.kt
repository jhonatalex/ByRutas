package com.example.byrutas

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.net.Uri

import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.byrutas.Tools.GPS_controler
import com.example.byrutas.Tools.Utils
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Double


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {


    private lateinit var myMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationP: Location

    private lateinit var jsonObjectRequest: JsonObjectRequest
    private lateinit var request: RequestQueue
    private lateinit var gpsTracker: GPS_controler

    private val scope = CoroutineScope(Dispatchers.Default)

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        gpsTracker = GPS_controler()
        request = Volley.newRequestQueue(applicationContext)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)



        ir.setOnClickListener {
            val intento1 = Intent(this, RutasActivity::class.java)
            startActivity(intento1)

        }


    }

    //METHOD STAR DE MAPS
    override fun onMapReady(googleMap: GoogleMap) {

        myMap = googleMap
        myMap.mapType = GoogleMap.MAP_TYPE_NORMAL       // CAMBIA EL TIPO DE MAPA
        myMap.isMyLocationEnabled = true                // UBICACION GPS ACTUAL
        myMap.uiSettings.isCompassEnabled = true        // BRUJULA
        myMap.uiSettings.isZoomControlsEnabled = true   // BOTONES DE ZOOM




        //PERMISOSS
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


        fusedLocationClient.lastLocation.addOnSuccessListener(this) { locationV ->

            if (locationV != null) {
                lastLocation = locationV
                val currentLatLong = LatLng(locationV.latitude, locationV.longitude)
                placeMarker(currentLatLong)
                myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 14f))
                Utils.MarkerDefault(myMap, applicationContext)
                myMap.setOnMapClickListener(this)
                myMap.setOnMarkerClickListener(this)
            }
        }


    }


    override fun onMarkerClick(marker: Marker): Boolean {
        AlertShow(marker.title, marker.position)
        return false
    }


    //CHANGE COLOR THE POINTER
    private fun placeMarker(location: LatLng) {
        val markerOption = MarkerOptions().position(location)
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        myMap.addMarker(markerOption)


    }

    fun AlertShow(title: String?, latLng: LatLng) {

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
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        locationP = location
                    }

                }

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Desea ir este punto?")
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setPositiveButton("Si", DialogInterface.OnClickListener { dialog, which ->
            Utils.cordenadas.destinationLat = latLng.latitude
            Utils.cordenadas.destinationLon = latLng.longitude
            MyAsyncTask()
        })
        builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()


    }

    fun MyAsyncTask() = scope.launch {

        Log.d("Asyntask", "Coordenadas")

        Utils.cordenadas.originLat = locationP.latitude
        Utils.cordenadas.originLon = locationP.longitude


        Log.d("INICIO LATITUDE", Utils.cordenadas.originLat.toString())
        Log.d("INICIO LOGITUDEO", Utils.cordenadas.originLon.toString())

        Log.d("FINAL LATITUDE", Utils.cordenadas.destinationLat.toString())
        Log.d("FINAL LONGITUDE", Utils.cordenadas.destinationLon.toString())


        ///TODO: CALL  A LA API
        ObtenerRuta(Utils.cordenadas.originLat.toString(), Utils.cordenadas.originLon.toString(),
                    Utils.cordenadas.destinationLat.toString(), Utils.cordenadas.destinationLon.toString())


    }


    override fun onMapClick(p0: LatLng?) {
        if (p0 != null) Utils.cordenadas.originLat = p0.latitude
        if (p0 != null) Utils.cordenadas.destinationLat = p0.latitude
        Toast.makeText(this, "SELECCIONE UN MARCADOR", Toast.LENGTH_LONG).show()
    }


    private fun ObtenerRuta(latInicial: String, lngInicial: String, latFinal: String, lngFinal: String) {
        val url =
                "https://maps.googleapis.com/maps/api/directions/json?origin=$latInicial,$lngInicial&destination=$latFinal,$lngFinal&key=&mode=drive"
        jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    var jRoutes: JSONArray? = null
                    var jLegs: JSONArray? = null
                    var jSteps: JSONArray? = null
                    try {


                        jRoutes = response.getJSONArray("routes")
                        for (i in 0 until jRoutes.length()) {
                            jLegs = (jRoutes[i] as JSONObject).getJSONArray("legs")
                            var path: List<HashMap<String, String>> = ArrayList()
                            for (j in 0 until jLegs.length()) {
                                jSteps = (jLegs[j] as JSONObject).getJSONArray("steps")
                                for (k in 0 until jSteps.length()) {
                                    var polyline = ""
                                    polyline =
                                            ((jSteps[k] as JSONObject)["polyline"] as JSONObject)["points"] as String
                                    val list = decodePoly(polyline)
                                    for (l in 0 until list.size) {
                                        val hm = HashMap<String, String>()
                                        hm["lat"] = Double.toString(list[l].latitude)
                                        hm["lng"] = Double.toString(list[l].longitude)
                                        path += hm
                                    }
                                }
                                Utils.routes = listOf(path)

                                //*DIBUJA LAS LINEAS**
                                drawLines()

                            }
                        }
                    } catch (e: JSONException) {
                        Log.e("ERROR", e.toString())
                        e.printStackTrace()
                    } catch (e: Exception) {
                        Log.e("DOBLE ERROR", e.toString())
                    }
                }
        ) { error ->
            Toast.makeText(applicationContext, "No se puede conectar $error", Toast.LENGTH_LONG)
                    .show()
            println()
            Log.d("ERROR: ", error.toString())
        }
        request.add(jsonObjectRequest)
    }



    private fun drawLines() {
        lateinit var points: ArrayList<LatLng>
        lateinit var lineOptions: PolylineOptions

        for (element in Utils.routes) {
            Log.d("TAMAÑO LISTA", Utils.routes.size.toString())
            points = ArrayList<LatLng>()
            lineOptions = PolylineOptions()
            val pathR: List<HashMap<String, String>> = element
            for (j in pathR.indices) {
                val point = pathR[j]
                val lat = point["lat"]!!.toDouble()
                val lng = point["lng"]!!.toDouble()
                val position = LatLng(lat, lng)
                points.add(position)
            }
            lineOptions.addAll(points)
            lineOptions.width(9F)
            lineOptions.color(Color.BLUE)

        }

        myMap.addPolyline(lineOptions)
    }


    private fun decodePoly(encoded: String): List<LatLng> {
        var poly: List<LatLng> = ArrayList()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val p = LatLng(
                    lat.toDouble() / 1E5,
                    lng.toDouble() / 1E5
            )
            poly += p
        }
        return poly
    }


}