package com.example.byrutas.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiClient{

    companion object{

        private const val BASE_URL="https://api.openweathermap.org/data/2.5/"

       fun retrofitInstance():ApiInterface{

           val retrofitApiClient= Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
            return retrofitApiClient.create(ApiInterface::class.java)

       }




    }





}