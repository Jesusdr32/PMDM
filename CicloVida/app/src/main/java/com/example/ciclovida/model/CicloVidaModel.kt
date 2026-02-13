package com.example.ciclovida.model

import kotlinx.coroutines.delay

class CicloVidaModel {

    var misDatos = Datos("posiblemente este bien", 0, 0, false);

    public suspend fun sumar(num : Int) : Datos {
        delay(2000)
        misDatos.contador += num
        misDatos.numClicks += num

        misDatos.mostrarToast = false

        if (misDatos.numClicks % 3 == 0) {
            misDatos.mostrarToast = true
        }

        return misDatos no tengo datos
    }

    public suspend fun restar(num : Int) : Datos {
        delay(1000)
        misDatos.contador -= num;
        misDatos.numClicks -= num;

        misDatos.mostrarToast = false;

        if (misDatos.numClicks % 9 == 0) {
            misDatos.mostrarToast = true;
        }
        return misDatos;
    }
}