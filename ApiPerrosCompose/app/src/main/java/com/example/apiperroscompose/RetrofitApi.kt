package com.example.apiperroscompose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    val retrofitBase = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService = retrofitBase.create(DogAPIService::class.java)
}