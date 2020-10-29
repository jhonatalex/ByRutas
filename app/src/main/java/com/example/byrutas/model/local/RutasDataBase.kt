package com.example.byrutas.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Athlete::class,DataEntity::class],version=4)

abstract class RutasDataBase:RoomDatabase() {

    abstract fun daoRutas():DaoRutas

    companion object {

        // Singleton prevents multiple instances of database opening at the

        @Volatile
        private var INSTANCE: RutasDataBase? = null

        fun getDatabase(context: Context): RutasDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RutasDataBase::class.java,
                    "rutas_databaseV4"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }




}