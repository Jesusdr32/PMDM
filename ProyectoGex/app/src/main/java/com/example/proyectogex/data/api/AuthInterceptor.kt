package com.example.proyectogex.data.api

import android.util.Log
import com.example.proyectogex.domain.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = SessionManager.token

        Log.d("AUTH", "TOKEN = $token")

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        val request = requestBuilder.build()

        Log.d("AUTH", "URL = $request.url")

        return chain.proceed(request)
    }
}