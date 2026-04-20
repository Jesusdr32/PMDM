package com.example.apiperroscompose

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPIService {
    @GET("/list/all")
    suspend fun getRazasPerros(): Response<RazasResponse>

    @GET("{raza}/images")
    suspend fun getFotosPerros(@Path("raza") raza: String): Response<DogRespuesta>
}