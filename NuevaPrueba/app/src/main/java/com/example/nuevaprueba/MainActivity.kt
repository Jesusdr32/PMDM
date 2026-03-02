package com.example.nuevaprueba

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nuevaprueba.databinding.ActivityMainBinding
import com.example.nuevaprueba.recycler.MiAdaptador
import com.example.nuevaprueba.viewmodel.RecyclerViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var miAdaptador : MiAdaptador
    private val miViewModel : RecyclerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        miViewModel.retornarLista()
        binding.btnBorrar.setOnClickListener {
            miViewModel.borrarAnimal(miAdaptador.posicionSeleccionada)
        }
        binding.btnAniadir.setOnClickListener {
            miViewModel.aniadirAnimal(binding.eTxtAnimal.text.toString(), miAdaptador.posicionSeleccionada)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                miViewModel.datos.collect {
                    when(it.estado) {
                        "ok" -> {
                            miAdaptador = MiAdaptador(it)
                            binding.recycler.layoutManager = LinearLayoutManager(
                                this@MainActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            binding.recycler.adapter = miAdaptador

                            //Metemos una division entre las filas
                            val midividerItemDecoration =
                                DividerItemDecoration(
                                    this@MainActivity,
                                    DividerItemDecoration.VERTICAL
                                )
                            binding.recycler.addItemDecoration(midividerItemDecoration)
                        }

                        "error" -> Toast.makeText(
                            this@MainActivity,
                            "Seleccione un animal a borrar",
                            Toast.LENGTH_LONG
                        ).show()

                        "add" -> miAdaptador.notifyDataSetChanged()
                        "del" -> miAdaptador.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}