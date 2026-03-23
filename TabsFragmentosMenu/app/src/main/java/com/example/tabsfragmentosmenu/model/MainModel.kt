package com.example.tabsfragmentosmenu.model

import kotlin.random.Random

class MainModel {

    suspend fun generarNum() : Datos {
        val num = Random.nextInt(1900, 2201)
        return Datos(num, 1)
    }
}