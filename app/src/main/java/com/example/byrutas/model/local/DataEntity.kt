package com.example.byrutas.model.local

import android.accounts.AuthenticatorDescription
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.byrutas.model.remote.pojo.Clouds
import com.google.gson.annotations.SerializedName


@Entity(tableName = "dataWeather_table")
data class DataEntity(

  @PrimaryKey
    val city:String,
    val longitudeL: Double,
    val latitudeL:Double,
    val clouds: Int,
    val cloudsDescription: String,
    val icon:String,
    val feelsLike: Double,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double,
    val humidity: Int,
    val pressure: Int,




)




