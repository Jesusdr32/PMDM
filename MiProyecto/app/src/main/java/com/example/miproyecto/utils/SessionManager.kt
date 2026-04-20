package com.example.miproyecto.utils

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("token", token).apply()
    }

    fun getToken(): String {
        return prefs.getString("token", "") ?: ""
    }

    fun saveUsername(username: String) {
        prefs.edit().putString("username", username).apply()
    }

    fun getUsername(): String {
        return prefs.getString("username", "") ?: ""
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}