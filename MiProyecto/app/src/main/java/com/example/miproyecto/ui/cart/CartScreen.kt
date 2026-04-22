package com.example.miproyecto.ui.cart

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miproyecto.data.dto.CartItemDto
import com.example.miproyecto.data.repository.CartRepository

@Composable
fun CartScreen() {
    val myViewModel: CartViewModel = viewModel()
    val cart = remember { mutableStateOf(listOf<CartItemDto>()) }

    LaunchedEffect(true) {
        cart.value = CartRepository().getCart().items
    }

    LazyColumn {
        items(cart.value) { item ->
            Text(item.product.name)
        }
    }
}

