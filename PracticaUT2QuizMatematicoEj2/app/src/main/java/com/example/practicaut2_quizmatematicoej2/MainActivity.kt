package com.example.practicaut2_quizmatematicoej2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var numAleatorio : Int = 0
    lateinit var cb2 : CheckBox
    lateinit var cb3 : CheckBox
    lateinit var cb5 : CheckBox
    lateinit var cb10 : CheckBox
    lateinit var cbNone : CheckBox
    lateinit var btnNumAleatorio : Button
    lateinit var btnResultado : Button
    lateinit var txtNumAleatorio : TextView
    lateinit var txtResultado : TextView
    lateinit var imgResultado : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cb2 = this.findViewById(R.id.cb2)
        cb3 = this.findViewById(R.id.cb3)
        cb5 = this.findViewById(R.id.cb5)
        cb10 = this.findViewById(R.id.cb10)
        cbNone = this.findViewById(R.id.cbNone)
        btnNumAleatorio = this.findViewById(R.id.btnNumAleatorio)
        btnResultado = this.findViewById(R.id.btnResultado)
        txtNumAleatorio = this.findViewById(R.id.txtNumAleatorio)
        txtResultado = this.findViewById(R.id.txtResultado)
        imgResultado = this.findViewById(R.id.imgResultado)
        btnResultado.isEnabled = false

        btnNumAleatorio.setOnClickListener {
            numAleatorio = (1000..2000).random()
            txtNumAleatorio.text = "¿Entre qué números es divisible el número ${numAleatorio}?"
            cb2.isChecked = false
            cb3.isChecked = false
            cb5.isChecked = false
            cb10.isChecked = false
            cbNone.isChecked = false
            txtResultado.text = ""
            imgResultado.visibility = View.INVISIBLE
            btnResultado.isEnabled = true
        }

        btnResultado.setOnClickListener {
            val checked = cb2.isChecked || cb3.isChecked || cb5.isChecked || cb10.isChecked || cb10.isChecked || cbNone.isChecked

            if (!checked) {
                Toast.makeText(this, "Debe escoger al menos una de las opciones", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val respuestaCorrecta = (cb2.isChecked == (numAleatorio % 2 == 0) &&
                    cb3.isChecked == (numAleatorio % 3 == 0) &&
                    cb5.isChecked == (numAleatorio % 5 == 0) &&
                    cb10.isChecked == (numAleatorio % 10 == 0) &&
                    cbNone.isChecked == ((numAleatorio % 2 != 0) && (numAleatorio % 3 != 0) && (numAleatorio % 5 != 0)))

            if (respuestaCorrecta) {
                txtResultado.text = "Correcto"
                imgResultado.visibility = View.VISIBLE
                imgResultado.setImageResource(R.drawable.ic_ok)
            } else {
                txtResultado.text = "Incorrecto"
                imgResultado.visibility = View.VISIBLE
                imgResultado.setImageResource(R.drawable.ic_ko)
            }
        }
    }
}