package com.example.miproyecto.ui.products

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miproyecto.data.dto.ProductDto
import com.example.miproyecto.data.repository.ProductRepository

@Composable
fun ProductsScreen() {
    val myViewModel: ProductsViewModel = viewModel()
    val products = remember { mutableListOf(listOf<ProductDto>()) }

    LaunchedEffect(true) {
        products.value = ProductRepository().getProducts()
    }

    LazyColumn {
        items(products.value) { product ->
            ProductCard(product)
        }
    }
}