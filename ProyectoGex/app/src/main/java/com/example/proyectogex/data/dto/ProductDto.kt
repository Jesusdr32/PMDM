package com.example.proyectogex.data.dto

data class ProductDto(
    val productId: Long,
    val productName: String,
    val productDescription: String,
    val productImage: String?,
    val productPrice: Double,
    val productDiscount: Int?,
    val categories: List<CategoryDto>
)
