package com.example.miproyecto.ui.cart

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miproyecto.data.dto.CartItemDto
import com.example.miproyecto.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    var cart by mutableStateOf(listOf<CartItemDto>())

    private val repo = CartRepository()

    fun load() {
        viewModelScope.launch {
            cart = repo.getCart().items
        }
    }

    fun remove(id: Long) {
        viewModelScope.launch {
            cart = repo.delete(id).items
        }
    }
}