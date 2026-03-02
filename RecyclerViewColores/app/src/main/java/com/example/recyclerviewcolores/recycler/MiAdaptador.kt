package com.example.recyclerviewcolores.recycler

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolores.R
import com.example.recyclerviewcolores.model.Datos
import androidx.core.graphics.toColorInt

class MiAdaptador(var misDatos : Datos) : RecyclerView.Adapter<MiVista>() {

    var posicionSeleccionada = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiVista {
        var miVista = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)

        return MiVista(miVista)
    }

    override fun onBindViewHolder(holder: MiVista, position: Int) {
        holder.nombre.text = misDatos.colores.get(position).nombre
        holder.codigo.text = misDatos.colores.get(position).codigo
        holder.fila.setBackgroundColor(misDatos.colores.get(position).codigo.toColorInt())

        if (position == posicionSeleccionada) {
            holder.nombre.setTextColor(misDatos.colores.get(position).codigo.toColorInt())
            holder.codigo.setTextColor(misDatos.colores.get(position).codigo.toColorInt())
            holder.fila.setBackgroundColor(Color.WHITE)
        } else {
            holder.nombre.setTextColor(Color.WHITE)
            holder.codigo.setTextColor(Color.WHITE)
            holder.fila.setBackgroundColor(misDatos.colores.get(position).codigo.toColorInt())
        }

        holder.fila.setOnClickListener {
            notifyItemChanged(posicionSeleccionada)
            posicionSeleccionada = position
            notifyItemChanged(posicionSeleccionada)
        }
    }

    override fun getItemCount(): Int {
        return misDatos.colores.size
    }

}