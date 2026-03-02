package com.example.recyclerviewcolores

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewcolores.databinding.ActivityMainBinding
import com.example.recyclerviewcolores.recycler.MiAdaptador
import com.example.recyclerviewcolores.viewModel.ColoresViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var miAdaptador : MiAdaptador

    private val miViewModel : ColoresViewModel by viewModels()

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
        miViewModel.retorarLista()


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                miViewModel.datos.collect {
                    miAdaptador = MiAdaptador(it)
                    binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recycler.adapter = miAdaptador

                    // Metemos una división entre las filas
                    val midividerItemDecoration = DividerItemDecoration(
                        this@MainActivity, DividerItemDecoration.VERTICAL
                    )
                    binding.recycler.addItemDecoration(midividerItemDecoration)
                }
            }
        }
    }
}