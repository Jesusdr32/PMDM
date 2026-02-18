package com.example.mvvmcalculadorav2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcalculadorav2.model.CalculadoraModel
import com.example.mvvmcalculadorav2.model.Dato
import kotlinx.coroutines.launch

class CalculadoraViewModel : ViewModel() {

    private val model = CalculadoraModel()

    private val _dato = MutableLiveData<Dato>(model.dato)
    val datoObservable : LiveData<Dato> get() = _dato

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    fun onNumberClick(number : String) {
        viewModelScope.launch {
            model.inputNumber(number)
            _dato.value = model.dato
        }
    }

    fun onOperationClick(op : String) {
        viewModelScope.launch {
            val msg = model.setOperation(op)
            if (msg != null) {
                _error.value = msg
            } else {
                _dato.value = model.dato
            }
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
