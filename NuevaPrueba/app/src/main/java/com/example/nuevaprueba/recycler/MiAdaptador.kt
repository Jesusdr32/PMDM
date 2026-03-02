package com.example.nuevaprueba.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nuevaprueba.R
import com.example.nuevaprueba.model.Datos

class MiAdaptador(var misDatos : Datos) : RecyclerView.Adapter<MiVista>() {

    var posicionSeleccionada = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiVista {
        var miVista = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)

        return MiVista(miVista)
    }

    override fun onBindViewHolder(
        holder: MiVista,
        position: Int
    ) {
        holder.miTexto.text = misDatos.lista.get(position)
        if (position == posicionSeleccionada) {
            holder.miTexto.setTextColor(Color.WHITE)
            holder.miTexto.setBackgroundColor(Color.RED)
        } else {
            holder.miTexto.setTextColor(Color.GREEN)
            holder.miTexto.setBackgroundColor(Color.WHITE)
        }
        holder.miTexto.setOnClickListener {
            notifyItemChanged(posicionSeleccionada)
            posicionSeleccionada = position
            notifyItemChanged(posicionSeleccionada)
        }
        /* holder.miTexto.setOnLongClickListener {
            holder.miTexto.setTextColor(Color.GREEN)
            holder.miTexto.setBackgroundColor(Color.WHITE)
            true
        } */
    }

    override fun getItemCount(): Int {
        return misDatos.lista.size
    }
}