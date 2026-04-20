package com.example.apiperroscompose

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListaPerrosState {
    val retrofitApi = RetrofitApi()
    suspend fun listaRazasPerros(): List<String> = withContext(Dispatchers.IO) {
        val respuesta = retrofitApi.retrofitService.getRazasPerros()

        if (respuesta.isSuccessful) {
            respuesta.body()?.message?.keys?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun recuperarFotos(raza: String): DogRespuesta = withContext(Dispatchers.IO) {
        val respuesta = retrofitApi.retrofitService.getFotosPerros(raza)

        if (respuesta.isSuccessful) {
            respuesta.body() ?: DogRespuesta("error", emptyList())
        } else {
            DogRespuesta("error", null)
        }
    }

}