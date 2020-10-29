package com.example.byrutas.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.security.auth.callback.PasswordCallback


@Entity
data class Athlete(

        @PrimaryKey val email:String,
        val password: String,
        val name:String,
        val weight:Double,
        val height:Double,
        val gender:Char,
        val city:String
)




