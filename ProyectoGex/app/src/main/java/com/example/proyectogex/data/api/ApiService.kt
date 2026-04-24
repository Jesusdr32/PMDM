package com.example.proyectogex.data.api

import com.example.proyectogex.data.dto.AddCartDto
import com.example.proyectogex.data.dto.CartResponseDto
import com.example.proyectogex.data.dto.CategoryDto
import com.example.proyectogex.data.dto.LoginRequestDto
import com.example.proyectogex.data.dto.LoginResponseDto
import com.example.proyectogex.data.dto.ProductDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{productId}")
    suspend fun getProductById(@Path("productId") productId: Long): ProductDto

    @GET("categories/{categoryId}/products")
    suspend fun getProductsByCategory(@Path("categoryId") categoryId: Long): List<ProductDto>

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("cart")
    suspend fun getCart(): CartResponseDto

    @POST("cart")
    suspend fun addToCart(@Body body: AddCartDto): CartResponseDto

    @DELETE("cart/{productId}")
    suspend fun deleteFromCart(@Path("productId") productId: Long): CartResponseDto

    @DELETE("cart")
    suspend fun clearCart(): CartResponseDto

}