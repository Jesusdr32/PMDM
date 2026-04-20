package com.example.composable2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MiViewModel : ViewModel() {
    private val miEstado = MiEstado()
    private val _cont = MutableStateFlow<Int>(0)

    val cont : StateFlow<Int> get() = _cont

    fun sumar(numSumar : Int) {
        viewModelScope.launch {
            _cont.value = miEstado.sumar(numSumar)
        }
    }
}