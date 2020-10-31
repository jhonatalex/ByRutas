package com.example.byrutas.Tools

 public data class Coordinate(

     var originLat: Double,
     var originLon:Double,
     var destinationLat:Double,
     val destinationLon:Double,


     )

 {

     constructor():this(-30.579156,-71.189903,
             -30.612819,-71.216488)



 }