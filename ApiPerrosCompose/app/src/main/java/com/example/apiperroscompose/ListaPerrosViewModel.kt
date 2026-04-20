package com.example.apiperroscompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaPerrosViewModel: ViewModel() {

    val myState: ListaPerrosState = ListaPerrosState()
    private val _listaRazas = MutableStateFlow<List<String>>(emptyList())
    val listaRazas: StateFlow<List<String>> get() = _listaRazas

    private val _detallePerro = MutableStateFlow<DogRespuesta?>(null)
    val detallePerro: StateFlow<DogRespuesta?> get() = _detallePerro

    fun cargarRazas() {
        viewModelScope.launch {
            _listaRazas.value = myState.listaRazasPerros()
        }
    }

    init {
        cargarRazas()
    }

    fun cargarDetallePerro(raza: String) {
        viewModelScope.launch {
            _detallePerro.value = myState.recuperarFotos(raza)
        }
    }
}