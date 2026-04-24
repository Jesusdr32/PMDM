package com.example.proyectogex.data.repository

import com.example.proyectogex.data.api.ApiService
import com.example.proyectogex.data.api.RetrofitClient
import com.example.proyectogex.data.dto.AddCartDto
import com.example.proyectogex.data.dto.CartResponseDto

class CartRepository(private val api: ApiService = RetrofitClient.api) {
    suspend fun getCart(): CartResponseDto {
        return api.getCart()
    }

    suspend fun addToCart(productId: Long, units: Int): CartResponseDto {
        return api.addToCart(AddCartDto(productId, units))
    }

    suspend fun removeFromCart(productId: Long): CartResponseDto {
        return api.deleteFromCart(productId)
    }

    suspend fun clearCart(): CartResponseDto {
        return api.clearCart()
    }
}