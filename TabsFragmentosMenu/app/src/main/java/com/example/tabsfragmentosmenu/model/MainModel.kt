package com.example.tabsfragmentosmenu.model

class MainModel {
    // Generar un número aleatorio entre 1900 y 2200
    fun generarNum() : Int {
        return (1900..2200).random()
    }

    // Validar si un número es bisiesto
    fun esBisiesto(num : Int) : Boolean {
        return (num % 4 == 0 && (num % 100 != 0 || num % 400 == 0))
    }

    // Validar si un número es divisible por cierta opción
    // Retorna 0 = correcto, -1 = incorrecto
    fun validarDivisible(num : Int, opcion : String) : Int {
        return when (opcion) {
            "2" -> if (num % 2 == 0) 0 else -1
            "3" -> if (num % 3 == 0) 0 else -1
            "5" -> if (num % 5 == 0) 0 else -1
            "10" -> if (num % 10 == 0) 0 else -1
            "ninguno" -> if (num % 2 != 0 && num % 3 != 0 && num % 5 != 0) 0 else -1
            else -> -1
        }
    }

    // Validar bisiesto según opción del usuario ("SI"/"NO")
    // Retorna 0 = correcto, -1 = incorrecto
    fun validarBisiesto(num : Int, opcion : String) : Int {
        val esBisiesto = esBisiesto(num)
        return if ((opcion == "SI" && esBisiesto) || (opcion == "NO" && !esBisiesto)) 0 else -1
    }
}