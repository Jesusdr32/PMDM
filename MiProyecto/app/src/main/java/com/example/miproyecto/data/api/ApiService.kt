package com.example.miproyecto.data.api

import com.example.miproyecto.data.model.CartItem
import com.example.miproyecto.data.model.GenericResponse
import com.example.miproyecto.data.model.LoginRequest
import com.example.miproyecto.data.model.LoginResponse
import com.example.miproyecto.data.model.Product
import com.google.gson.stream.JsonToken
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("product")
    suspend fun getProducts(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("category") category: String
    ): Response<List<Product>>

    @GET("cart")
    suspend fun getCart(
        @Header("Authorization") token: String
    ): Response<List<CartItem>>

    @POST("cart/add")
    suspend fun addToCart(
        @Header("Authorization") token: String,
        @Body request: AddCartRequest): Response<GenericResponse>

    @DELETE("cart/remove/{id}")
    suspend fun removeFromCart(
        @Header("Authorization") token: String,
        @Path("id") id: Long): Response<GenericResponse>
}