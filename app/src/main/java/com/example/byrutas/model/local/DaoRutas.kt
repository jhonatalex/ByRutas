package com.example.byrutas.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.byrutas.model.remote.pojo.Main


@Dao
interface DaoRutas {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneUser(user: Athlete)

    @Query("SELECT * from Athlete")
    fun getAllAthlete(): LiveData<List<Athlete>>


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertDataWeather(value: DataEntity)

    @Query("SELECT * from dataWeather_table")
    fun getAllWeather(): LiveData<List<DataEntity>>



}