package com.example.practicaut3jugandodados

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var txtSaldo : TextView
    lateinit var txtSaldo2 : TextView
    lateinit var txtDado1 : TextView
    lateinit var txtDado2 : TextView
    lateinit var btnParImpar : MaterialButton
    lateinit var btnMayorMenor : MaterialButton
    lateinit var select : Spinner
    lateinit var eTxtApuesta : EditText
    lateinit var btnLanzar : MaterialButton
    lateinit var imgDados : ImageView
    lateinit var myCoordinator : CoordinatorLayout
    private lateinit var miCorrutina : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtSaldo = this.findViewById(R.id.txtSaldo)
        txtSaldo2 = this.findViewById(R.id.txtSaldo2)
        txtDado1 = this.findViewById(R.id.txtDado1)
        txtDado2 = this.findViewById(R.id.txtDado2)
        btnParImpar = this.findViewById(R.id.btnParImpar)
        btnMayorMenor = this.findViewById(R.id.btnMayorMenor)
        select = this.findViewById(R.id.select)
        eTxtApuesta = this.findViewById(R.id.eTxtApuesta)
        btnLanzar = this.findViewById(R.id.btnLanzar)
        imgDados = this.findViewById(R.id.imgDados)
        myCoordinator = this.findViewById(R.id.myCoordinator)



        select.isEnabled = false
        imgDados.visibility = View.VISIBLE
        txtSaldo2.text = "100"

        btnParImpar.setOnClickListener {
            cargarSelect(listOf("PAR", "IMPAR"))
        }

        btnMayorMenor.setOnClickListener {
            cargarSelect(listOf("MAYOR O IGUAL QUE 7", "MENOR QUE 7"))
        }

        btnLanzar.setOnClickListener {
            if (!select.isEnabled) {
                Snackbar.make(myCoordinator, "Selecciona un tipo de juego", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!validarApuesta()) return@setOnClickListener

            //Lanzamos la corrutina para los dados
            miCorrutina = lifecycleScope.launch {
                lanzarDados()
            }
        }

    }

    fun cargarSelect(lista : List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        select.adapter = adapter
        select.isEnabled = true
    }

    fun validarApuesta() : Boolean {
        val saldo = txtSaldo2.text.toString().toInt()
        val texto = eTxtApuesta.text.toString()

        if (texto.isEmpty()) {
            Snackbar.make(myCoordinator, "Introduce una apuesta, está vacío.", Snackbar.LENGTH_LONG).show()
            return false
        }

        val apuesta = texto.toInt()
        if (apuesta <= 0) {
            Snackbar.make(myCoordinator, "La apuesta no puede ser menor o igual que 0.", Snackbar.LENGTH_LONG).show()
            return false
        }

        if (apuesta > saldo) {
            Snackbar.make(myCoordinator, "No tienes suficiente saldo.", Snackbar.LENGTH_LONG).show()
            return false
        }
        return true
    }

      suspend fun lanzarDados() {
          btnLanzar.isEnabled = false

          txtDado1.text = ""
          txtDado2.text = ""

          Glide.with(this)
              .load(R.drawable.dado_imagen_animada_0092)
              .into(imgDados)

          delay(3000)


          var saldo = txtSaldo2.text.toString().toInt()
          val cantidadApuesta = eTxtApuesta.text.toString().toInt()
          val opcion = select.selectedItem.toString()

          val dado1 = (1..6).random()
          val dado2 = (1..6).random()
          val suma = dado1 + dado2
          txtDado1.text = dado1.toString()
          txtDado2.text = dado2.toString()

          val gana = when (opcion) {
              "PAR" -> suma % 2 == 0
              "IMPAR" -> suma % 2 != 0
              "MAYOR O IGUAL QUE 7" -> suma >= 7
              "MENOR QUE 7" -> suma < 7
              else -> false
          }

          if (gana) {
              saldo += cantidadApuesta
              imgDados.setImageResource(R.drawable.ganar_dados)
          } else {
              saldo -= cantidadApuesta
              imgDados.setImageResource(R.drawable.perder_dados)
          }

          txtSaldo2.text = saldo.toString()

          if (saldo <= 0) {
              delay(2000)
              txtDado1.text = ""
              txtDado2.text = ""
              imgDados.setImageResource(R.drawable.bancarrota)
              AlertDialog.Builder(this)
                  .setTitle("Fin de la partida")
                  .setMessage("Te has quedado sin saldo.")
                  .setPositiveButton("Salir", DialogInterface.OnClickListener { dialog, which ->
                      dialog.dismiss()
                      finish()
                  }).show()
              return
          }

          delay(1000)

          AlertDialog.Builder(this)
              .setTitle("¿Seguir jugando?")
              .setMessage("¿Deseas continuar?")
              .setCancelable(false)
              .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, which ->
                  dialog.dismiss()
                  btnLanzar.isEnabled = true
              })
              .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                  dialog.dismiss()
                  finish()
              }).show()
      }

}