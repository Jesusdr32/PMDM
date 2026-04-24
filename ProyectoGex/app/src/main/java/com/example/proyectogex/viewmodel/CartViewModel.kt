package com.example.proyectogex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectogex.data.repository.CartRepository
import com.example.proyectogex.states.CartState
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val state = CartState(CartRepository())

    val isLoading get() = state.isLoading

    val errorMessage get() = state.errorMessage

    val cartResponse get() = state.cartResponse

    fun loadCart() {
        viewModelScope.launch {
            state.loadCart()
        }
    }

    fun addProduct(productId: Long, units: Int) {
        viewModelScope.launch {
            state.addProduct(productId, units)
        }
    }

    fun removeProduct(productId: Long) {
        viewModelScope.launch {
            state.removeProduct(productId)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            state.clearCart()
        }
    }
}