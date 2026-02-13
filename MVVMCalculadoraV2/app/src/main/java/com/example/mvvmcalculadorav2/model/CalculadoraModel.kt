package com.example.mvvmcalculadorav2.model

import kotlinx.coroutines.delay

class CalculadoraModel {

    var miDato = Dato("ok", 0, 0, 0)

    public suspend fun sumar(num1 : Int, num2 : Int) : Dato {
        delay(2000)
        miDato.num1 = num1
        miDato.num2 = num2
        miDato.res = miDato.num1 + miDato.num2

        return miDato
    }

    public suspend fun restar(num1 : Int, num2 : Int) : Dato {
        delay(2000)
        miDato.num1 = num1
        miDato.num2 = num2
        miDato.res = miDato.num1 - miDato.num2

        return miDato
    }

    public suspend fun multiplicar(num1 : Int, num2 : Int) : Dato {
        soy rarillo
    }
}