package com.example.byrutas.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.byrutas.model.local.DaoRutas
import com.example.byrutas.model.local.DataEntity
import com.example.byrutas.model.remote.RetrofitApiClient
import com.example.byrutas.model.remote.pojo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryApp (private val myDao:DaoRutas){

    private val mRetrofit=RetrofitApiClient.retrofitInstance()
    //LiveData

    fun getDataTheDataBase():LiveData<List<DataEntity>>{
        return myDao.getAllWeather()
    }






    fun getDataFromApi(city:String)= CoroutineScope(Dispatchers.IO).launch {
        val service=kotlin.runCatching {  mRetrofit.getWeatherData(city)}

        service.onSuccess { it ->
            when(it.code()) {
                in 200..299 -> it.body()?.let {



                    //val Data=convertApiToEntity(it.weatherGeneral, it.main,it.cordR,it.cloudsR,it.nubes,city)

                   // val Data=convertApiToEntityPB(it.main.,city)

                    Log.e("PRUEBABBAAAAAA", it.main.temp.toString())
                    Log.e("NUBOSIDAD", it.clouds.all.toString())
                    Log.e("CIUDDAD", it.name.toString())
                    Log.e("LATITUD", it.coord.lat.toString())
                    Log.e("LONGITUD", it.coord.lon.toString())
                    Log.e("LONGITUD", it.weather.toString())



                    //myDao.insertDataWeather(Data) //-->LA GUARD EN BASE D EDATOS
                }
                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }



        }


        service.onFailure {
            Log.e("ERROR", it.message.toString())


        }


        }


    private fun convertApiToEntityPB(tempParameter: Main,city:String) : DataEntity{

        return (DataEntity(     city = city,
                                feelsLike = tempParameter.feelsLike,
                                temp= tempParameter.temp,
                                tempMax= tempParameter.tempMax,
                                tempMin=tempParameter.tempMin,
                                humidity=  tempParameter.humidity,
                                pressure=  tempParameter.pressure ,

                                longitudeL= 25.5,
                                latitudeL = 35.5,
                                clouds=  5,
                                cloudsDescription="Nublado",
                                icon = "nubes.icon",

                ))
    }



    private fun convertApiToEntity(father: Weather,tempParameter: Main, location: Coord, state:Clouds,
                                   nubes:WeatherX,city:String) : DataEntity{

        return (DataEntity(             city = city,
                                        longitudeL= location.lon,
                                        latitudeL = location.lat,
                                        clouds=  state.all,
                                        cloudsDescription=nubes.description,
                                        icon = nubes.icon,
                                        feelsLike = tempParameter.feelsLike,
                                        temp= tempParameter.temp,
                                        tempMax= tempParameter.tempMax,
                                        tempMin=tempParameter.tempMin,
                                        humidity=  tempParameter.humidity,
                                        pressure=  tempParameter.pressure ,

                                       ))
    }




}



