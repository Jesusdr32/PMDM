package com.example.miproyecto.data.repository

import com.example.miproyecto.data.api.RetrofitClient

class ProductRepository {
    suspend fun getProducts(token: String, page: Int, category: String) =
        RetrofitClient.apiService.getProducts(token, page, category)
}