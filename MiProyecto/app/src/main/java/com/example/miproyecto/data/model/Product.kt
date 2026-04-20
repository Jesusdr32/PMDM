package com.example.miproyecto.data.model

import java.io.Serializable

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
): Serializable