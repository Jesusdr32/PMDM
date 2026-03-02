package com.example.recyclerviewcolores.recycler

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolores.R

class MiVista(miFila : View) : RecyclerView.ViewHolder(miFila) {
    var nombre = miFila.findViewById<TextView>(R.id.txtNombre)
    var codigo = miFila.findViewById<TextView>(R.id.txtCodigo)
    var fila = miFila.findViewById<LinearLayout>(R.id.fila)

}