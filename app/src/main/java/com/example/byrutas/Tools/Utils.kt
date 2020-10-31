package com.example.byrutas.Tools

import android.content.Context
import com.google.android.gms.maps.GoogleMap

public class Utils {


    companion object{

         var cordenadas:Coordenadas=Coordenadas()
        //var cordenadas:Coordinate=Coordinate()

        var routes: List< List<HashMap<String, String>>> =  mutableListOf()

        fun MarkerDefault(nMap: GoogleMap, context: Context){
            Marcadores(nMap, context).addMarkerDefault()

        }


    }

}