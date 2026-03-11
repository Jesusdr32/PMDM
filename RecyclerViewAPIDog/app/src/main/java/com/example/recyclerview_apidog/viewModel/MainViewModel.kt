package com.example.recyclerview_apidog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview_apidog.model.Datos
import com.example.recyclerview_apidog.model.DogRespuesta
import com.example.recyclerview_apidog.model.MainState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val myEstado = MainState()
    private val _datos = MutableLiveData<DogRespuesta>()
    val datos : LiveData<DogRespuesta> get() = _datos

    fun devuelveFotos(raza : String) {
        viewModelScope.launch {
            _datos.value = myEstado.recuperarFotos(raza)
        }
    }

    private val _datosScroll = MutableLiveData<Datos>(Datos(null.toString(), null, null, ArrayList()))

    val datosScroll : LiveData<Datos> get() = _datosScroll

    fun recuperarFotosPaginacion(raza : String) {
        viewModelScope.launch {
            _datosScroll.value = myEstado.recuperarFotosPaginacion(raza)
        }
    }

    fun scrollFotos() {
        viewModelScope.launch {
            _datosScroll.value = myEstado.scrollFotos()
        }
    }
}