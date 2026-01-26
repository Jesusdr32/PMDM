package com.example.practicaut2_quizmatematicoej1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnNumRandom : Button
    lateinit var txtNumRandom : TextView
    lateinit var txtBisiesto : TextView
    lateinit var rbGroup : RadioGroup
    lateinit var rbSi : RadioButton
    lateinit var rbNo : RadioButton
    lateinit var btnResultado : Button
    lateinit var txtResultado : TextView
    lateinit var fondo_amarillo : Switch

    private var year = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnNumRandom = this.findViewById(R.id.btnNumRandom)
        txtNumRandom = this.findViewById(R.id.txtNumRandom)
        txtBisiesto = this.findViewById(R.id.txtBisiesto)
        rbGroup = this.findViewById(R.id.rbGroup)
        rbSi = this.findViewById(R.id.rbSi)
        rbNo = this.findViewById(R.id.rbNo)
        btnResultado = this.findViewById(R.id.btnResultado)
        txtResultado  =this.findViewById(R.id.txtResultado)
        fondo_amarillo = this.findViewById(R.id.fondo_amarillo)

        btnNumRandom.setOnClickListener {
            year = (1900..2500).random()
            txtNumRandom.text = year.toString()
            rbGroup.clearCheck()
            txtResultado.text = ""
        }

        btnResultado.setOnClickListener {
            if (rbGroup.checkedRadioButtonId == -1) {
                txtResultado.text = "Debe escoger una de las opciones"
                txtResultado.setTextColor(Color.BLUE)
            } else {
                val esBisiesto = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

                if (rbSi.isChecked == esBisiesto) {
                    txtResultado.text = "Correcto"
                    txtResultado.setTextColor(Color.GREEN)
                } else {
                    txtResultado.text = "Error"
                    txtResultado.setTextColor(Color.RED)
                }
            }
        }

        fondo_amarillo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                findViewById<android.view.View>(R.id.main).setBackgroundColor(Color.YELLOW)
            } else {
                findViewById<android.view.View>(R.id.main).setBackgroundColor(Color.WHITE)
            }
        }

    }
}