package com.example.ciclovida.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciclovida.model.CicloVidaModel
import com.example.ciclovida.model.Datos
import kotlinx.coroutines.launch

class CicloVidaViewModel : ViewModel(){
    val miModelo = CicloVidaModel()
    private val _misDatos = MutableLiveData<Datos>()
    val misDatosObservables : LiveData<Datos> get() = _misDatos
    public fun sumar(num : Int) {
        viewModelScope.launch {
            _misDatos.value= miModelo.sumar(num)
        }
    }

    public fun restar(num : Int) {
        viewModelScope.launch {
            _misDatos.value= miModelo.restar(num)
        }
    }
}