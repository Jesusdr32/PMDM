package com.example.miproyecto.data.api

import com.example.miproyecto.data.dto.AddProductToCartDto
import com.example.miproyecto.data.dto.CartResponseDto
import com.example.miproyecto.data.dto.CategoryDto
import com.example.miproyecto.data.dto.LoginRequestDto
import com.example.miproyecto.data.dto.LoginResponseDto
import com.example.miproyecto.data.dto.ProductDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Auth
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

    @POST("auth/refresh")
    suspend fun refresh(@Body request: Any): Any

    // Products
    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Long): ProductDto

    // Categories
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("categories/{id}/products")
    suspend fun getProductsByCategory(@Path("id") id: Long): List<ProductDto>

    // Cart
    @GET("cart")
    suspend fun getCart(): CartResponseDto

    @POST("cart")
    suspend fun addToCart(@Body dto: AddProductToCartDto): CartResponseDto

    @DELETE("cart/{productId}")
    suspend fun removeFromCart(@Path("productId") id: Long): CartResponseDto

    @DELETE("cart")
    suspend fun clearCart(): CartResponseDto
}