package com.example.proyectogex.data.repository

import com.example.proyectogex.data.api.ApiService
import com.example.proyectogex.data.api.RetrofitClient
import com.example.proyectogex.data.dto.ProductDto

class ProductRepository(private val api: ApiService = RetrofitClient.api) {

    suspend fun getAllProducts(): List<ProductDto> {
        return api.getProducts().map { it.withMockStock() }
    }

    suspend fun getProductsPage(page: Int, size: Int): List<ProductDto> {
        return api.getProducts(page, size).map { it.withMockStock() }
    }

    suspend fun getProductsByCategory(categoryId: Long): List<ProductDto> {
        return api.getProductsByCategory(categoryId).map { it.withMockStock() }
    }

    suspend fun getProductsByCategoryPage(categoryId: Long, page: Int, size: Int): List<ProductDto> {
        return api.getProductsByCategory(categoryId, page, size).map { it.withMockStock() }
    }

    suspend fun getProductById(productId: Long): ProductDto {
        return api.getProductById(productId).withMockStock()
    }

    // ---------------------------------------------------------------------
    // TODO: STOCK FICTICIO PROVISIONAL
    // Cuando el backend exponga el campo "productStock" en ProductDto.java,
    // elimina la función withMockStock() y los .map { it.withMockStock() }
    // de arriba — el campo llegará directamente desde la API.
    // ---------------------------------------------------------------------
    private fun ProductDto.withMockStock(): ProductDto {
        // Si el backend ya está devolviendo productStock, lo respetamos.
        if (productStock != null) return this

        // Reparto determinista en base al id para que puedas ver todos los
        // estados de la UI sin tener que tocar nada:
        //   - 0       -> "Agotado" (botón deshabilitado)
        //   - 1..4    -> "¡Solo quedan X unidades!"
        //   - 5..24   -> "Stock: X unidades"
        val fakeStock = ((productId * 7) % 25).toInt()
        return copy(productStock = fakeStock)
    }
}
