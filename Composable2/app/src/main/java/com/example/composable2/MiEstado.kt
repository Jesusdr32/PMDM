package com.example.composable2

import android.util.Log

class MiEstado {
    var num : Int = 0

    suspend fun sumar(numSumar : Int) : Int {
        num += numSumar

        Log.e("COMPOSE", num.toString())
        Log.e("COMPOSE2", numSumar.toString())

        return num
    }
}