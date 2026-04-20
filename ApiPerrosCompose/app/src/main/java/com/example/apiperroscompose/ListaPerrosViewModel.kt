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

    fun listaRazas() {
        viewModelScope.launch {
            _listaRazas.value = myState.listaRazasPerros()
        }
    }

    init {
        listaRazas()
    }
    private val _detallePerro = MutableStateFlow<>()
    val detallePerro: StateFlow<> get() = _detallePerro

    fun detallePerro(raza: String) {
        viewModelScope.launch {
            _detallePerro.value = myState
        }
    }
}