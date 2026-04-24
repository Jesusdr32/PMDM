package com.example.proyectogex.data.dto

data class CartItemDto(
    val productName: String,
    val unitPrice: Double,
    val discount: Int,
    val discounntedPrice: Double,
    val units: Int,
    val totalPrice: Double
)
