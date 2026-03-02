package com.example.recyclerviewcolores.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewcolores.model.Color
import com.example.recyclerviewcolores.model.Datos
import com.example.recyclerviewcolores.model.ColoresModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColoresViewModel : ViewModel() {
    var miModelo = ColoresModel()

    private var _datos = MutableStateFlow<Datos>(Datos("", mutableListOf<Color>()))

    val datos : StateFlow<Datos> get() = _datos

    public fun retorarLista() {
        viewModelScope.launch {
            _datos.value = miModelo.retornarLista()
        }
    }
}