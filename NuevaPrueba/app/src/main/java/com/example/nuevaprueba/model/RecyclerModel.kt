package com.example.nuevaprueba.model

import androidx.recyclerview.widget.RecyclerView

class RecyclerModel {

    var animales = mutableListOf("Caballo", "Vaca", "Tigre", "León", "Camaleón", "Camello", "Perro", "Gato", "Pájaro", "Lombriz", "Gusano", "Escarabajo", "Mosquito", "Delfín")

    public suspend fun retornarLista() : Datos {
        return Datos("ok", animales)
    }

    public suspend fun aniadir(animal : String, posicion : Int) : Datos{
        var miPosicion = animales.size
        if (posicion != RecyclerView.NO_POSITION) { miPosicion = posicion }
        animales.add(miPosicion, animal)

        return Datos("add", animales)
    }

    public suspend fun borrar(posicion : Int): Datos {
        if (posicion == RecyclerView.NO_POSITION) {
            return Datos("error", animales)
        } else {
            animales.removeAt(posicion)
            return Datos("del", animales)
        }
    }
}