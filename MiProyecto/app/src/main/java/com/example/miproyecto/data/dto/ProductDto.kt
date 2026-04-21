package com.example.miproyecto.data.dto

data class ProductDto(
    val id: Long,
    val name: String,
    val description: String,
    val image: String?,
    val price: Double
)