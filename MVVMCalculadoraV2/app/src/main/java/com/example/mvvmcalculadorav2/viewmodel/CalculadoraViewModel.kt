package com.example.mvvmcalculadorav2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcalculadorav2.model.CalculadoraModel
import com.example.mvvmcalculadorav2.model.Dato
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class CalculadoraViewModel : ViewModel() {

    private val model = CalculadoraModel()

    private var _dato = MutableStateFlow<Dato>(Dato("", "", "", "", false, ""))
    val datoObservable : StateFlow<Dato> get() = _dato

    private var _error = MutableStateFlow<String>("")
    val error : StateFlow<String> get() = _error

    fun onNumberClick(number : String) {
        viewModelScope.launch {
            _dato.value = model.inputNumber(number)
        }
    }

    fun onOperationClick(op : String) {
        viewModelScope.launch {
           _dato.value = model.setOperation(op)
        }
    }

    fun onEqualsClick() {
        viewModelScope.launch {
            val resultado = model.calculate()
            if (resultado.estado.startsWith("error")) {
                _error.value = resultado.estado
            } else {
                _dato.value = resultado
            }
        }
    }

    fun onClearClick() {
        viewModelScope.launch {
            model.clearAll()
            _dato.value = model.dato
        }
    }
}
