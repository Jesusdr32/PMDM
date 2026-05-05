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

    var allProducts: List<ProductDto> = emptyList()
        private set

    var products by mutableStateOf<List<ProductDto>>(emptyList())
        private set

    private var page = 0
    private val pageSize = 3

    var canLoadMore by mutableStateOf(true)
        private set

    var selectedProduct by mutableStateOf<ProductDto?>(null)
        private set

    var selectedCategoryId by mutableStateOf<Long?>(null)
        private set

    suspend fun loadAllProducts() {
        isLoading = true
        errorMessage = null

        try {
            allProducts = productRepository.getAllProducts()

            resetPagination()

            loadNextPage()
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
            allProducts = productRepository.getProductsByCategory(categoryId)

            resetPagination()

            loadNextPage()
        } catch (e : Exception) {
            errorMessage = e.message ?: "Error filtrando productos por categoría"
        } finally {
            isLoading = false
        }
    }

    fun loadNextPage() {
        if (!canLoadMore) return

        val start = page * pageSize
        val end = (start + pageSize).coerceAtMost(allProducts.size)

        if (start >= allProducts.size) {
            canLoadMore = false
            return
        }

        val nextItems = allProducts.subList(start, end)

        products = products + nextItems
        page++

        if (end >= allProducts.size) {
            canLoadMore = false
        }
    }

    private fun resetPagination() {
        products = emptyList()
        page = 0
        canLoadMore = true
    }
}