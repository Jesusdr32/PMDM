package com.example.miproyecto.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miproyecto.data.model.LoginResponse
import com.example.miproyecto.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val repository = AuthRepository()

    val loginResult = MutableLiveData<LoginResponse>()
    val error = MutableLiveData<String>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                if (response.isSuccessful && response.body() != null) {
                    loginResult.postValue(response.body())
                } else {
                    error.postValue("Usuario o contraseña incorrectos")
                }
            } catch (e: Exception) {
                error.postValue(e.message)
            }
        }
    }
}