package com.example.miproyecto.data.repository

import com.example.miproyecto.data.api.RetrofitClient
import com.example.miproyecto.data.dto.AddProductToCartDto

class CartRepository {
    suspend fun getCart() = RetrofitClient.api.getCart()

    suspend fun add(dto: AddProductToCartDto) = RetrofitClient.api.addToCart(dto)

    suspend fun delete(id: Long) = RetrofitClient.api.removeFromCart(id)
}