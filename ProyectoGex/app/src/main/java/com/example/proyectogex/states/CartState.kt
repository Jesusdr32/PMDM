package com.example.proyectogex.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectogex.data.dto.CartItemDto
import com.example.proyectogex.data.dto.CartResponseDto
import com.example.proyectogex.data.repository.CartRepository

class CartState(private val cartRepository: CartRepository) {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var cartResponse by mutableStateOf<CartResponseDto?>(null)
        private set

    suspend fun loadCart() {
        isLoading = true
        errorMessage = null

        try {
            val response = cartRepository.getCart()
            updateState(response)

        } catch (e : Exception) {
            errorMessage = e.message ?: "Error cargando carrito"
        } finally {
            isLoading = false
        }
    }

    suspend fun addProduct(productId: Long, units: Int) {
        isLoading = true
        errorMessage = null

        try {
            val response = cartRepository.addToCart(productId, units)
            updateState(response)

        } catch (e : Exception) {
            errorMessage = e.message ?: "Error añadiendo producto"
        } finally {
            isLoading = false
        }
    }

    suspend fun removeProduct(productId: Long) {
        isLoading = true
        errorMessage = null

        try {
            val response = cartRepository.removeFromCart(productId)
            updateState(response)

        } catch (e : Exception) {
            errorMessage = e.message ?: "Error eliminando producto"
        } finally {
            isLoading = false
        }
    }

    suspend fun clearCart() {
        isLoading = true
        errorMessage = null

        try {
            val response = cartRepository.clearCart()
            updateState(response)

        } catch (e : Exception) {
            errorMessage = e.message ?: "Error vaciando carrito"
        } finally {
            isLoading = false
        }
    }

    private fun updateState(response: CartResponseDto) {
        cartResponse = response
    }
}