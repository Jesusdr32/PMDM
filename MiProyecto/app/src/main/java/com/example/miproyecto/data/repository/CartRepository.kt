package com.example.miproyecto.data.repository

import com.example.miproyecto.data.api.RetrofitClient
import com.example.miproyecto.data.model.AddCartRequest

class CartRepository {
    suspend fun getCart(token: String) =
        RetrofitClient.apiService.getCart(token)

    suspend fun addToCart(token: String, productId: Int, quantity: Int) =
        RetrofitClient.apiService.addToCart(token, AddCartRequest(productId, quantity))

    suspend fun removeFromCart(token: String, id: Long) =
        RetrofitClient.apiService.removeFromCart(token, id)
}