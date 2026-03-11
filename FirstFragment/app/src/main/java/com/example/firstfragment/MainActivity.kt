package com.example.firstfragment

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var miBtnMain : Button
    private lateinit var miFcvMain : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        miBtnMain = findViewById<Button>(R.id.btnMain)
        miFcvMain = findViewById<FrameLayout>(R.id.fcvMain)

        miBtnMain.setOnClickListener {
            var miFragmentoManager : FragmentManager = supportFragmentManager
            var miFragmentoTransaccion : FragmentTransaction = miFragmentoManager.beginTransaction()
            //var miFragmentoExe = miFragmento()
            var miFragmentoExe = miFragmento.newInstance("Texto de la pantalla", 7)
            miFragmentoTransaccion.add(R.id.fcvMain, miFragmentoExe).commit()
        }
    }
}