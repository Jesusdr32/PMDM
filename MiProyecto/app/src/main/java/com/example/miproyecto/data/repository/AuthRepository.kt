package com.example.miproyecto.data.repository

import com.example.miproyecto.data.api.RetrofitClient
import com.example.miproyecto.data.dto.LoginRequestDto

class AuthRepository {
    suspend fun login(dto: LoginRequestDto) = RetrofitClient.api.login(dto)
}