package com.example.calculadoracompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MiViewModel : ViewModel() {

    private val miEstado = MiEstado()

    private val _cont = MutableStateFlow<Double>(0.0)

    val cont : StateFlow<Double> get() = _cont

    fun sumar(num1 : Double, num2 : Double) {
        viewModelScope.launch {
            _cont.value = miEstado.sumar(num1, num2)
        }
    }

    fun restar(num1 : Double, num2 : Double) {
        viewModelScope.launch {
            _cont.value = miEstado.restar(num1, num2)
        }
    }

    fun multiplicar(num1 : Double, num2 : Double) {
        viewModelScope.launch {
            _cont.value = miEstado.multiplicar(num1, num2)
        }
    }

    fun dividir(num1 : Double, num2 : Double) {
        viewModelScope.launch {
            _cont.value = miEstado.dividir(num1, num2)
        }
    }
}