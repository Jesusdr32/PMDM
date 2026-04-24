package com.example.proyectogex.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.proyectogex.data.repository.AuthRepository
import com.example.proyectogex.domain.SessionManager

class LoginState(private val authRepository: AuthRepository) {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    suspend fun login(username: String, password: String) {
        isLoading = true
        errorMessage = null
        loginSuccess = false

        try {
            val token = authRepository.login(username, password)

            if (token.isNotEmpty()) {
                SessionManager.token = token
                loginSuccess = true
            }
        } catch (e: Exception) {
            errorMessage = e.message ?: "Usuario o contraseña incorrectos"
        } finally {
            isLoading = false
        }
    }

    fun reset() {
        isLoading = false
        errorMessage = null
        loginSuccess = false
    }
}