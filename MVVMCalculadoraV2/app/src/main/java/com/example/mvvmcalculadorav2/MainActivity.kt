package com.example.mvvmcalculadorav2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mvvmcalculadorav2.databinding.ActivityMainBinding
import com.example.mvvmcalculadorav2.viewmodel.CalculadoraViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val miViewModel: CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        miViewModel.datoObservable.observe(this) {
            binding.txtResultado.text = if (it.num.isEmpty()) "0.0" else it.num
            binding.txtOperacion.text = it.estado
        }

        miViewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        val botones = listOf(binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9)
        botones.forEach { btn ->
            btn.setOnClickListener { miViewModel.onNumberClick(btn.text.toString()) }
        }

        binding.btnSuma.setOnClickListener { miViewModel.onOperationClick("+") }
        binding.btnResta.setOnClickListener { miViewModel.onOperationClick("-") }
        binding.btnMulti.setOnClickListener { miViewModel.onOperationClick("*") }
        binding.btnDivi.setOnClickListener { miViewModel.onOperationClick("/") }

        binding.btnIgual.setOnClickListener { miViewModel.onEqualsClick() }
        binding.btnClear.setOnClickListener { miViewModel.onClearClick() }
    }
}
