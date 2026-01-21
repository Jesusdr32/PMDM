package com.example.practicaut1_calculadorasimple

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var txt1 : TextView
    lateinit var num1 : EditText
    lateinit var txt2 : TextView
    lateinit var num2 : EditText
    lateinit var btnSumar : Button
    lateinit var btnRestar : Button
    lateinit var btnMultiplicar : Button
    lateinit var btnDividir : Button
    lateinit var txtRes : TextView
    lateinit var txtResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt1 = this.findViewById(R.id.txt1)
        num1 = this.findViewById(R.id.num1)
        txt2 = this.findViewById(R.id.txt2)
        num2 = this.findViewById(R.id.num2)
        btnSumar = this.findViewById(R.id.btnSumar)
        btnRestar = this.findViewById(R.id.btnRestar)
        btnMultiplicar = this.findViewById(R.id.btnMultiplicar)
        btnDividir = this.findViewById(R.id.btnDividir)
        txtRes = this.findViewById(R.id.txtRes)
        txtResultado = this.findViewById(R.id.txtResultado)

        btnSumar.setOnClickListener {
            var num1 = num1.text.toString().toDouble()
            var num2 = num2.text.toString().toDouble()
            var operacion = num1 + num2
            txtResultado.text = operacion.toString()
        }

        btnRestar.setOnClickListener {
            var num1 = num1.text.toString().toDouble()
            var num2 = num2.text.toString().toDouble()
            var operacion = num1 - num2
            txtResultado.text = operacion.toString()
        }

        btnMultiplicar.setOnClickListener {
            var num1 = num1.text.toString().toDouble()
            var num2 = num2.text.toString().toDouble()
            var operacion = num1 * num2
            txtResultado.text = operacion.toString()
        }

        btnDividir.setOnClickListener {
            var num1 = num1.text.toString().toDouble()
            var num2 = num2.text.toString().toDouble()
            var operacion = num1 / num2
            txtResultado.text = operacion.toString()
        }
    }
}