package com.example.finalproject.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
  private const val BASE_URL = "http://10.0.2.2:90/"

    // private  const val BASE_URL ="http://localhost:90/"


    var token:String?=null
    private val okhttp =  OkHttpClient.Builder()

    //creating retrofit builder
    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp.build())
    //create retrofit instance
    private val retrofit = retrofitBuilder.build()
    //generic function
    fun<T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)

    }

}