package com.example.proyectogex.data.repository

import com.example.proyectogex.data.api.ApiService
import com.example.proyectogex.data.api.RetrofitClient
import com.example.proyectogex.data.dto.CategoryDto
import com.example.proyectogex.data.dto.ProductDto

class CategoryRepository(private val api: ApiService = RetrofitClient.api) {
    suspend fun getCategories(): List<CategoryDto> {
        return api.getCategories()
    }
}