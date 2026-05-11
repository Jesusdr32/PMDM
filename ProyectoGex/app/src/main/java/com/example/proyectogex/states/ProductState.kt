package com.example.proyectogex.states

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.proyectogex.data.dto.ProductDto
import com.example.proyectogex.data.repository.ProductRepository
import com.example.proyectogex.utils.NetworkErrorHandler
import kotlinx.coroutines.delay

class ProductState(private val productRepository: ProductRepository) {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private var allProducts: List<ProductDto> = emptyList()

    var products by mutableStateOf<List<ProductDto>>(emptyList())
        private set

    private var page = 0
    private val pageSize = 3

    var canLoadMore by mutableStateOf(false)
        private set

    var selectedProduct by mutableStateOf<ProductDto?>(null)
        private set

    var selectedCategoryId by mutableStateOf<Long?>(null)
        private set

    suspend fun loadAllProducts() {
        isLoading = true
        errorMessage = null
        selectedCategoryId = null
        try {
            allProducts = productRepository.getAllProducts()
            resetAndLoadFirst()
        } catch (e: Exception) {
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    suspend fun loadProductById(productId: Long) {
        isLoading = true
        errorMessage = null
        try {
            selectedProduct = productRepository.getProductById(productId)
        } catch (e: Exception) {
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    suspend fun filterByCategory(categoryId: Long) {
        isLoading = true
        errorMessage = null
        selectedCategoryId = categoryId
        try {
            allProducts = productRepository.getProductsByCategory(categoryId)
            resetAndLoadFirst()
        } catch (e: Exception) {
            errorMessage = NetworkErrorHandler.getMessage(e)
        } finally {
            isLoading = false
        }
    }

    suspend fun loadNextPage() {
        if (!canLoadMore || isLoading) return
        isLoading = true
        delay(400)
        val start = page * pageSize
        val end = (start + pageSize).coerceAtMost(allProducts.size)
        products = products + allProducts.subList(start, end)
        page++
        if (end >= allProducts.size) canLoadMore = false
        isLoading = false
    }

    private fun resetAndLoadFirst() {
        products = emptyList()
        page = 0
        val end = pageSize.coerceAtMost(allProducts.size)
        products = allProducts.subList(0, end)
        page = 1
        canLoadMore = allProducts.size > pageSize
    }
}