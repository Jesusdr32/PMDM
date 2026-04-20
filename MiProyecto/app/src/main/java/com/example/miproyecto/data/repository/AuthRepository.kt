package com.example.miproyecto.data.repository

import com.example.miproyecto.data.api.RetrofitClient
import com.example.miproyecto.data.model.LoginRequest

class AuthRepository {
    suspend fun login(email: String, password: String) =
        RetrofitClient.apiService.login(LoginRequest(email, password))
}