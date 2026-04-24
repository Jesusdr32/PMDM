package com.example.proyectogex.data.repository

import com.example.proyectogex.data.api.ApiService
import com.example.proyectogex.data.api.RetrofitClient
import com.example.proyectogex.data.dto.ProductDto

class ProductRepository(private val api: ApiService = RetrofitClient.api) {
    suspend fun getAllProducts(): List<ProductDto> {
        return api.getProducts()
    }

    suspend fun getProductsByCategory(categoryId: Long): List<ProductDto> {
        return api.getProductsByCategory(categoryId)
    }

    suspend fun getProductById(productId: Long): ProductDto {
        return api.getProductById(productId)
    }
}