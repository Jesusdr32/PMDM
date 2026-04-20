package com.example.calculadoracompose

import java.math.BigDecimal

class MiEstado {
    var num1 : Double = 0.0
    var num2 : Double = 0.0

    suspend fun sumar(num1 : Double, num2 : Double) : Double {
        return num1 + num2
    }

    suspend fun restar(num1 : Double, num2 : Double) : Double {
        return num1 - num2
    }

    suspend fun multiplicar(num1 : Double, num2 : Double) : Double {
        return num1 * num2
    }

    suspend fun dividir(num1 : Double, num2 : Double) : Double {
        return num1 / num2
    }
}