package com.example.conversion

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.conversion.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var miCorrutina: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnSinHilos.setOnClickListener {
            binding.pb1.setProgress(0)
            binding.pb1.max = 100
            for (i in 1..10){
                tarea()
                binding.pb1.setProgress(i * 10)
            }
            binding.tvTexto.text = "Tarea Finalizada"
        }
        binding.btnHola.setOnClickListener {
            Toast.makeText(this, "Aprendiendo corrutinas", Toast.LENGTH_SHORT).show()
        }
        binding.btnConHilos.setOnClickListener {
            binding.pb1.setProgress(0)
            binding.pb1.max = 100
            miCorrutina = lifecycleScope.launch {
                for (i in 1..10){
                    doSomethingUsefulOne()
                    binding.pb1.setProgress(i * 10)
                }
                binding.tvTexto.text = "Tarea Finalizada"
            }
        }
        binding.btnCancelar.setOnClickListener {
            miCorrutina.cancel()
            binding.tvTexto.text = "Tarea cancelada"
        }
    }
    fun tarea(){
        Thread.sleep(1000)
    }
    suspend fun doSomethingUsefulOne(): Int {
        delay(1000) // pretend we are doing something useful here
        return 13
    }
}