package com.example.tabsfragmentosmenu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabsfragmentosmenu.model.Datos
import com.example.tabsfragmentosmenu.model.MainModel
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val miModelo = MainModel()

    private val _datos = MutableLiveData(Datos())
    val datos : LiveData<Datos> get() = _datos

    fun generarNum() {
        viewModelScope.launch {
            _datos.value = miModelo.generarNum()
        }
    }

    fun validarBisiesto(respuesta : Boolean) {
        val num = _datos.value?.numGenerado
        if (num == null || num == 0) return

        val esBisiesto = (num % 4 == 0 && (num % 100 != 0 || num % 400 == 0))
        val estado = if (respuesta == esBisiesto) 0 else -1
        _datos.value = _datos.value?.copy(estado = estado)
    }

    fun validarDivisible(
        divisible2 : Boolean,
        divisible3 : Boolean,
        divisible5 : Boolean,
        divisible10 : Boolean,
        ninguno : Boolean
    ) {
        val num = _datos.value?.numGenerado
        if (num == null || num == 0) return

        val divisibles = listOf(
            divisible2 to 2,
            divisible3 to 3,
            divisible5 to 5,
            divisible10 to 10
       ).filter { it.first }.map { it.second }

        val correcto = if (divisibles.isEmpty()) {
            ninguno && listOf(2,3,5,10).all { num % it != 0 }
        } else {
            !ninguno && divisibles.all { num % it == 0 } && listOf(2,3,5,10).filter { it !in divisibles }.all { num % it != 0 }
        }

        _datos.value = _datos.value?.copy(estado = if (correcto) 0 else -1)
    }

    fun setEstado(correcto : Boolean) {
        viewModelScope.launch {
            val estado = if (correcto) 0 else -1
            val current = _datos.value
            _datos.value = current.copy(estado = estado)
        }
    }
}