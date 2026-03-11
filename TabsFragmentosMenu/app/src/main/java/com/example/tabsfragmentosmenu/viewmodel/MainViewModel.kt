package com.example.tabsfragmentosmenu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabsfragmentosmenu.model.Datos
import com.example.tabsfragmentosmenu.model.MainModel

class MainViewModel : ViewModel() {

    private val model = MainModel()

    private val _datos = MutableLiveData<Datos>()
    val datos : LiveData<Datos> get() = _datos

    // Generar número y resetear estado
    fun generarNum() {
        val num = model.generarNum()
        _datos.value = Datos(num, 1, mutableListOf())
    }

    // Validar Bisiesto
    fun validarBisiesto(opcion : String) {
        val num = _datos.value?.numGenerado ?: return
        val estadoResultado = model.validarBisiesto(num, opcion)
        _datos.value = _datos.value?.copy(estado = estadoResultado, resultado = mutableListOf(estadoResultado))
    }

    // Validar Divisible
    fun validarDivisible(opcion : String) {
        val num = _datos.value?.numGenerado ?: return
        val resultado = mutableListOf(model.validarDivisible(num, opcion))
        val estadoGlobal = if (resultado.all { it == 0 }) 0 else -1
        _datos.value = _datos.value?.copy(estado = estadoGlobal, resultado = resultado)
    }
}