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


                    val Data=convertApiToEntityPB(it.main, it.clouds,it.name,it.coord,it.weather)

                    Log.e("PRUEBABBAAAAAA", it.main.temp.toString())
                    Log.e("NUBOSIDAD", it.clouds.all.toString())
                    Log.e("CIUDDAD", it.name.toString())
                    Log.e("LATITUD", it.coord.lat.toString())
                    Log.e("LONGITUD", it.coord.lon.toString())
                    Log.e("LONGITUD", it.weather.toString())
                    
                    myDao.insertDataWeather(Data) //-->LA GUARD EN BASE D EDATOS
                }
                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }



        }


        service.onFailure {
            Log.e("ERROR", it.message.toString())


        }


        }


    private fun convertApiToEntityPB(tempParameter: Main, cloud:Clouds, name:String, cord:Coord, desc: List<WeatherX>) : DataEntity{

        return (DataEntity(     city = name,
                                feelsLike = tempParameter.feelsLike,
                                temp= tempParameter.temp,
                                tempMax= tempParameter.tempMax,
                                tempMin=tempParameter.tempMin,
                                humidity=  tempParameter.humidity,
                                pressure=  tempParameter.pressure ,

                                longitudeL= cord.lon,
                                latitudeL = cord.lat,
                                clouds=  cloud.all,
                                cloudsDescription= decodingArrayDescrip(desc),
                                icon = decodingArrayIcon(desc),

                ))
    }

    private fun decodingArrayDescrip(list:List<WeatherX> ):String{
        var Parametro=""
        list.map {
         Parametro = it.description
        }
        return  Parametro
    }

    private fun decodingArrayIcon(list:List<WeatherX> ):String{
        var Parametro=""
        list.map {
            Parametro = it.icon
        }
        return  Parametro
    }





}



