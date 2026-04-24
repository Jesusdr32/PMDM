package com.example.proyectogex.data.dto

data class CartResponseDto(
    val products: List<CartItemDto>,
    val distinctProducts: Long,
    val totalUnits: Long,
    val totalPrice: Double
)
