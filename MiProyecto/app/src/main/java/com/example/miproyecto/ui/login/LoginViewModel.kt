package com.example.miproyecto.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miproyecto.data.dto.LoginRequestDto
import com.example.miproyecto.data.repository.AuthRepository
import com.example.miproyecto.domain.SessionManager
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var loading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    private val repo = AuthRepository()

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                loading = true

                val res = repo.login(LoginRequestDto(username, password))

                SessionManager.token = res.accessToken
                SessionManager.username = username

                onSuccess()
            } catch (e: Exception) {
                error = "Login error"
            } finally {
                loading = false
            }
        }
    }
}