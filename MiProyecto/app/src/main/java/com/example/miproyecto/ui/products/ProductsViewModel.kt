package com.example.miproyecto.ui.products

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miproyecto.data.dto.ProductDto
import com.example.miproyecto.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    var products by mutableStateOf(listOf<ProductDto>())
    var filtered by mutableStateOf(listOf<ProductDto>())
    var category by mutableStateOf("all")

    private val repo = ProductRepository()

    fun load() {
        viewModelScope.launch {
            val data = repo.getProducts()
            products = data
            filtered = data
        }
    }

    fun filter(cat: String) {
        category = cat
        filtered = if (cat == "all") products
        else products.filter { it.category == cat }
    }
}