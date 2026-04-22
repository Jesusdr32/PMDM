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

    suspend fun recuperarFotos(raza: String, subraza: String? = null): DogRespuesta = withContext(Dispatchers.IO) {
        val respuesta = if (subraza.isNullOrEmpty()) {
            retrofitApi.retrofitService.getFotosPerros(raza)
        } else {
            retrofitApi.retrofitService.getFotosSubraza(raza, subraza)
        }

        if (respuesta.isSuccessful) {
            respuesta.body() ?: DogRespuesta("error", emptyList())
        } else {
            DogRespuesta("error", emptyList())
        }
    }

}