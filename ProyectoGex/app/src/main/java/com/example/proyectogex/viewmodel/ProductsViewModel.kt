package com.example.proyectogex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectogex.data.repository.ProductRepository
import com.example.proyectogex.states.ProductState
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private val state = ProductState(ProductRepository())

    val isLoading get() = state.isLoading

    val errorMessage get() = state.errorMessage

    val products get() = state.products

    val selectedProduct get() = state.selectedProduct

    val selectedCategoryId get() = state.selectedCategoryId

    fun loadAllProducts() {
        viewModelScope.launch {
            state.loadAllProducts()
        }
    }

    fun loadProductById(productId: Long) {
        viewModelScope.launch {
            state.loadProductById(productId)
        }
    }

    fun loadProductsByCategory(categoryId: Long) {
        viewModelScope.launch {
            state.filterByCategory(categoryId)
        }
    }
}