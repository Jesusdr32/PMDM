package com.example.tabsfragmentosmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.tabsfragmentosmenu.viewmodel.MainViewModel

class fragmentoBisiesto : Fragment() {

    private lateinit var rg: RadioGroup
    private lateinit var rbSi: RadioButton
    private lateinit var rbNo: RadioButton
    private lateinit var btnValidar: Button
    private lateinit var tvResultado: TextView

    private val miViewModel: MainViewModel by viewModels({requireActivity()})
    private var respuestaSeleccionada : Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.fragment_fragmento_bisiesto, container, false)
        rg = view.findViewById(R.id.rg)
        rbSi = view.findViewById(R.id.rbSi)
        rbNo = view.findViewById(R.id.rbNo)
        btnValidar = view.findViewById(R.id.btnValidarBisiesto)
        tvResultado = view.findViewById(R.id.tvEstadoBisiesto)
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

        rg.setOnCheckedChangeListener { _, checkedId ->
            respuestaSeleccionada = when (checkedId) {
                R.id.rbSi -> true
                R.id.rbNo -> false
                else -> null
            }
        }

        btnValidar.setOnClickListener {
            val num = miViewModel.datos.value?.numGenerado ?: return@setOnClickListener
            if (respuestaSeleccionada == null) {
                Toast.makeText(requireContext(), "Selecciona una opción", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val esBisiesto = (num % 4 == 0 && (num % 100 != 0 || num % 400 == 0))
            val correcto = respuestaSeleccionada == esBisiesto

            miViewModel.setEstado(correcto)
        }
    }
}