package com.example.byrutas.model.remote

import com.example.byrutas.model.remote.pojo.Example
import com.example.byrutas.model.remote.pojo.Main
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {

   /* @GET("weather?appid=0610fcc058f1c27074979a1408102775&units=metric&lang=es")
    suspend fun getWeatherData(@Query("q") name:String):Response<Example> */

    @GET("weather?appid=0610fcc058f1c27074979a1408102775&units=metric")
    fun getWeatherDataPrueba(@Query("q") name:String): Call<Example>


    @GET("weather?appid=0610fcc058f1c27074979a1408102775&units=metric&lang=es")
    suspend fun getWeatherData(@QueryMap() location:Map<String,String> ):Response<Example>

}