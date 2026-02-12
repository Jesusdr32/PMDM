package com.example.ciclovida

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.ciclovida.databinding.ActivityMainBinding
import com.example.ciclovida.model.Datos
import com.example.ciclovida.viewmodel.CicloVidaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var misDatos = Datos("ok", 0, 0, true)
    private val miViewModel : CicloVidaViewModel by viewModels()

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

        binding.btn1.setOnClickListener {
            miViewModel.sumar(1)
        }

        binding.btn2.setOnClickListener {
            miViewModel.restar(1)
        }

        miViewModel.misDatosObservables.observe(this, {
            misDatosRecibidos ->
            binding.txt.text = misDatosRecibidos.contador.toString()
            if (misDatosRecibidos.mostrarToast) {
                Toast.makeText(this, "Hemos llegado a cinco clicks", Toast.LENGTH_LONG).show()
            }
        })
    }

}