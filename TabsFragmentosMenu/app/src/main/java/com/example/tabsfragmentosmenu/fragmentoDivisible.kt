package com.example.tabsfragmentosmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.tabsfragmentosmenu.viewmodel.MainViewModel

class fragmentoDivisible : Fragment() {
    private lateinit var cb2 : CheckBox
    private lateinit var cb3 : CheckBox
    private lateinit var cb5 : CheckBox
    private lateinit var cb10 : CheckBox
    private lateinit var cbNinguno : CheckBox
    private lateinit var btnValidar : Button
    private lateinit var tvResultado : TextView

    private val miViewModel : MainViewModel by viewModels({requireActivity()})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fragmento_divisible, container, false)
        cb2 = view.findViewById(R.id.cb2)
        cb3 = view.findViewById(R.id.cb3)
        cb5 = view.findViewById(R.id.cb5)
        cb10 = view.findViewById(R.id.cb10)
        cbNinguno = view.findViewById(R.id.cbNinguno)
        btnValidar = view.findViewById(R.id.btnValidarDivisible)
        tvResultado = view.findViewById(R.id.tvEstadoDivisible)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        miViewModel.datos.observe(viewLifecycleOwner, Observer { data ->
            val num = data.numGenerado
            if (num != 0) {
                when (data.estado) {
                    1 -> {
                        tvResultado.text = "Pendiente"
                        tvResultado.setTextColor(resources.getColor(android.R.color.holo_blue_bright, null))
                    }

                    0 -> {
                        tvResultado.text = "Correcto"
                        tvResultado.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
                    }

                    -1 -> {
                        tvResultado.text = "Incorrecto"
                        tvResultado.setTextColor(resources.getColor(android.R.color.holo_red_light, null))
                    }

                    else -> tvResultado.text = ""
                }
            }
        })

        btnValidar.setOnClickListener {
            val num = miViewModel.datos.value?.numGenerado ?: return@setOnClickListener

            val divisibles = listOf(
                cb2.isChecked to 2,
                cb3.isChecked to 3,
                cb5.isChecked to 5,
                cb10.isChecked to 10
            ).filter { it.first }.map { it.second }

            val esCorrecto = if (divisibles.isEmpty()) {
                cbNinguno.isChecked && listOf(2,3,5,10).none { num % it == 0 }
            } else {
                cbNinguno.isChecked.not() && divisibles.all { num % it == 0 } && listOf(2,3,5,10).filter { it !in divisibles }.all { num % it != 0 }
             }

            miViewModel.setEstado(esCorrecto)
        }
    }
}