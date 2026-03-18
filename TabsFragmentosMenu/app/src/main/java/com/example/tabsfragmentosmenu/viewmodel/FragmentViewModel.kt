package com.example.tabsfragmentosmenu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabsfragmentosmenu.model.Datos
import com.example.tabsfragmentosmenu.model.MainModel

class FragmentViewModel : ViewModel() {

    private val model = MainModel()

    private val _datos = MutableLiveData<Datos>()
    val datos : LiveData<Datos> get() = _datos

    // Validar Bisiesto
    fun validarBisiesto(opcion : String) {
        val num = _datos.value!!.numGenerado
        val estadoResultado = model.validarBisiesto(num, opcion)
        _datos.value = Datos(num, estadoResultado, mutableListOf(estadoResultado))
    }

    // Validar Divisible
    fun validarDivisible(opcion : String) {
        val num = _datos.value!!.numGenerado
        val resultado = mutableListOf(model.validarDivisible(num, opcion))
        val estadoGlobal = if (resultado.all { it == 0 }) 0 else -1
        _datos.value = Datos(num, estadoGlobal, resultado)
    }
}