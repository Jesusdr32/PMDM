package com.example.miproyecto.data.model

data class CartItem(
    val id: Long,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val image: String
)