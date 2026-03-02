package com.example.nuevaprueba.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuevaprueba.model.Datos
import com.example.nuevaprueba.model.RecyclerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.collections.mutableListOf

class RecyclerViewModel : ViewModel() {

    var miModelo = RecyclerModel()
    private var _datos = MutableStateFlow<Datos>(Datos("", mutableListOf<String>()))

    val datos : StateFlow<Datos> get() = _datos

    public fun retornarLista() {
        viewModelScope.launch {
            _datos.value = miModelo.retornarLista()
        }
    }

    public fun aniadirAnimal(animal : String, posicion : Int) {
        viewModelScope.launch {
            _datos.value = miModelo.aniadir(animal, posicion)
        }
    }

    public fun borrarAnimal(posicion : Int) {
        viewModelScope.launch {
            _datos.value = miModelo.borrar(posicion)
        }
    }

}