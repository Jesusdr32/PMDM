package com.example.apiperroscompose

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPIService {
    @GET("breeds/list/all")
    suspend fun getRazasPerros(): Response<RazasResponse>

    @GET("breed/{raza}/images")
    suspend fun getFotosPerros(@Path("raza") raza: String): Response<DogRespuesta>

    @GET("breed/{raza}/{subraza}/images")
    suspend fun getFotosSubraza(@Path("raza") raza: String, @Path("subraza") subraza: String): Response<DogRespuesta>
}