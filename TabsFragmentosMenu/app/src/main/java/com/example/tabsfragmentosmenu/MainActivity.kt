package com.example.tabsfragmentosmenu

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tabsfragmentosmenu.viewmodel.MainViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mtb : MaterialToolbar
    private lateinit var btnGenerar : Button
    private lateinit var tvNum : TextView
    private lateinit var tbl : TabLayout
    private lateinit var fml : FrameLayout

    private val miViewModel: MainViewModel by viewModels()
    private var fragmentoActual : Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mtb = findViewById(R.id.mtb)
        btnGenerar = findViewById(R.id.btnGenerar)
        tvNum = findViewById(R.id.tvNum)
        tbl = findViewById(R.id.tbl)
        fml = findViewById(R.id.fml)

        setSupportActionBar(mtb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mtb.setNavigationOnClickListener { finish() }

        btnGenerar.setOnClickListener {
            miViewModel.generarNum()
        }

        tbl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab : TabLayout.Tab) {
                cargarFragmentoSeleccionado()
            }

            override fun onTabReselected(tab : TabLayout.Tab?) {}
            override fun onTabUnselected(tab : TabLayout.Tab?) {}
        })

        miViewModel.datos.observe(this, Observer { data ->
            tvNum.text = data.numGenerado.toString()
            if (data.numGenerado != 0) {
                cargarFragmentoSeleccionado()
            }
        })
}
    private fun cargarFragmentoSeleccionado() {
        val num = miViewModel.datos.value?.numGenerado ?: 0
        if (num == 0) {
            Toast.makeText(this@MainActivity, "Genera un número antes", Toast.LENGTH_LONG).show()
            return
        }

        fragmentoActual = when (tbl.selectedTabPosition) {
            0 -> fragmentoBisiesto()
            1 -> fragmentoDivisible()
            else -> null
        }

        fragmentoActual?.let {
            supportFragmentManager.beginTransaction()
                .replace(fml.id, it)
                .commit()
        }

        mtb.title = when (tbl.selectedTabPosition) {
            0 -> "Bisiesto"
            1 -> "Divisible"
            else -> "Fragmentos"
        }
    }
}