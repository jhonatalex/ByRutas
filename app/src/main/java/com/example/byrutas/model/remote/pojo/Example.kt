package com.example.byrutas.model.remote.pojo

import com.google.gson.annotations.SerializedName

 data class Example (
         @SerializedName("main")
         val main: Main,
         @SerializedName("clouds")
         val clouds:Clouds,
         @SerializedName("name")
         val name:String,
         @SerializedName("coord")
         val coord: Coord,
         @SerializedName("weather")
         val weather: List<WeatherX>,

         /*




     */
 )