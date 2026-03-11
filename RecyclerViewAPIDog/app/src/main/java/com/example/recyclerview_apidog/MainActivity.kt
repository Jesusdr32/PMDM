package com.example.recyclerview_apidog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_apidog.databinding.ActivityMainBinding
import com.example.recyclerview_apidog.model.Datos
import com.example.recyclerview_apidog.model.DogRespuesta
import com.example.recyclerview_apidog.recycler.MyAdaptador
import com.example.recyclerview_apidog.viewModel.MainViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var misDatos: Datos

    private val miViewModel : MainViewModel by viewModels()

    private lateinit var myAdapter : MyAdaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) {v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mLayout = LinearLayoutManager(this)
        binding.rvPerros.layoutManager = mLayout

        binding.bt2.setOnClickListener {
            val raza = binding.edt2.text.toString().trim().lowercase()

            if (raza.isEmpty()) {
                Toast.makeText(this, "Introduce una raza", Toast.LENGTH_LONG).show()
            } else {
                miViewModel.recuperarFotosPaginacion(raza)
            }
        }

        miViewModel.datos.observe(this@MainActivity) {
            when (it.status) {
                "success" -> {
                    myAdapter = MyAdaptador (it)
                    // Y el adaptador se lo asignamos al recycler
                    binding.rvPerros.adapter = myAdapter
                }
                "error" -> Toast.makeText(this@MainActivity, "No hay foto de esa raza", Toast.LENGTH_SHORT).show()
            }
        }

        miViewModel.datosScroll.observe(this@MainActivity) {
            when (it.status) {
                "success" -> {
                    if (it.paginaActual == 1) {
                        misDatos = it
                        myAdapter = MyAdaptador(DogRespuesta(it.status, it.message))
                        // Y el adaptador se lo asignamos al recycler
                        binding.rvPerros.adapter = myAdapter
                    } else {
                        myAdapter.notifyItemRangeInserted(it.paginaActual!!*10, it.message!!.size)
                    }
                }
                "error" -> Toast.makeText(this@MainActivity, "No hay fotos de esa raza", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvPerros.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var finalScroll = false

                if (mLayout.findLastVisibleItemPosition() % 10 >= 9 &&
                    mLayout.findLastVisibleItemPosition() / 10 == (misDatos.paginaActual!! - 1)) {

                    finalScroll = true
                }

                if (finalScroll && misDatos.paginaActual!! < misDatos.numPaginas!!) {

                    com.google.android.material.snackbar.Snackbar
                        .make(binding.root, "Si desea recuperar más fotos pulse:",
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                        .setAction("Cargar más fotos") {

                            miViewModel.scrollFotos()

                        }.show()
                }
            }
        })
    }
}