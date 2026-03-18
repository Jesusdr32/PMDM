package com.example.intentsejemplo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentsejemplo.databinding.Activity2Binding
import com.example.intentsejemplo.databinding.ActivityMainBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding : Activity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val myIntent = intent

        binding.txtV2.text = myIntent.getStringExtra("mensaje")

        binding.btn4.setOnClickListener {
            val myIntentVuelta = Intent()
            myIntentVuelta.putExtra("mensajeVuelta", "Vuelvo de la actividad 2")

            setResult(RESULT_OK, myIntentVuelta)
            this.finish()
        }
    }
}