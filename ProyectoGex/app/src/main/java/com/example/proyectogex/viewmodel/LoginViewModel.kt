package com.example.proyectogex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectogex.data.repository.AuthRepository
import com.example.proyectogex.states.LoginState
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val state = LoginState(AuthRepository())

    val isLoading get() = state.isLoading
    val errorMessage get() = state.errorMessage
    val loginSuccess get() = state.loginSuccess

    fun login(username: String, password: String) {
        viewModelScope.launch {
            state.login(username, password)
        }
    }

    fun reset() {
        state.reset()
    }
}