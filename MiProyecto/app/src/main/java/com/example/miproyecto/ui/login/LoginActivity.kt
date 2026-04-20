package com.example.miproyecto.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.miproyecto.ui.main.MainActivity
import com.example.miproyecto.utils.SessionManager

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.getEmail.text.toString()
            val password = binding.getPassword.text.toString()

            viewModel.login(email, password)
        }

        viewModel.loginResult.observe(this) {
            sessionManager.saveToken("Bearer ${it.token}")
            sessionManager.saveUsername(it.username)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}