package com.example.miproyecto.data.repository

import com.example.miproyecto.data.api.RetrofitClient

class ProductRepository {
    suspend fun getProducts() = RetrofitClient.api.getProducts()
}