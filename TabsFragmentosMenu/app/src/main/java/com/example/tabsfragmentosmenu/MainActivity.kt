package com.example.tabsfragmentosmenu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tabsfragmentosmenu.viewmodel.MainViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var mtb : MaterialToolbar
    private lateinit var btnGenerar : Button
    private lateinit var tvNum : TextView
    private lateinit var tbl : TabLayout

    private val miViewModel : MainViewModel by viewModels()

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

        setSupportActionBar(mtb)
        supportActionBar!!.hide()

        tbl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab : TabLayout.Tab) {
                when (tab.text) {
                    "Bisiesto" -> replaceFragment(fragmentoBisiesto())
                    "Divisible" -> replaceFragment(fragmentoDivisible())
                }
            }

            override fun onTabReselected(tab : TabLayout.Tab) {}
            override fun onTabUnselected(tab : TabLayout.Tab) {}
        })

        btnGenerar.setOnClickListener {
            miViewModel.generarNum()
        }

        miViewModel.datos.observe(this@MainActivity) {
            tvNum.text = it.numGenerado.toString()
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}