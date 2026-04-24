package com.example.proyectogex.data.repository

import com.example.proyectogex.data.api.ApiService
import com.example.proyectogex.data.api.RetrofitClient
import com.example.proyectogex.data.dto.LoginRequestDto
import com.example.proyectogex.domain.SessionManager

class AuthRepository(private val api: ApiService = RetrofitClient.api) {
    suspend fun login(username: String, password: String): String {
        val response = api.login(
            LoginRequestDto(username, password)
        )

        SessionManager.token = response.accessToken
        SessionManager.username = username

        return response.accessToken
    }

    fun logout() {
        SessionManager.token = null
        SessionManager.username = null
    }
}