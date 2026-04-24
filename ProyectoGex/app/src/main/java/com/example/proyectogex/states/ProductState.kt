package com.example.proyectogex.states

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.proyectogex.data.dto.ProductDto
import com.example.proyectogex.data.repository.ProductRepository

class ProductState(private val productRepository: ProductRepository) {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var products by mutableStateOf<List<ProductDto>>(emptyList())
        private set

    var selectedProduct by mutableStateOf<ProductDto?>(null)

    var selectedCategoryId by mutableStateOf<Long?>(null)
        private set

    suspend fun loadAllProducts() {
        isLoading = true
        errorMessage = null

        try {
            products = productRepository.getAllProducts()
        } catch (e: Exception) {
            errorMessage = e.message ?: "Error cargando productos"
        } finally {
            isLoading = false
        }
    }

    suspend fun loadProductById(productId: Long) {
        isLoading = true
        errorMessage = null

        try {
            selectedProduct = productRepository.getProductById(productId)
        } catch (e : Exception) {
            errorMessage = e.message ?: "Error cargando producto"
        } finally {
            isLoading = false
        }
    }

    suspend fun filterByCategory(categoryId: Long) {
        isLoading = true
        errorMessage = null
        selectedCategoryId = categoryId

        try {
            products = productRepository.getProductsByCategory(categoryId)
        } catch (e : Exception) {
            errorMessage = e.message ?: "Error filtrando productos por categoría"
        } finally {
            isLoading = false
        }
    }
}