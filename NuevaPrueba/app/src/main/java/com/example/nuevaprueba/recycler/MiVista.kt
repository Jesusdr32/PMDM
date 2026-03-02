package com.example.nuevaprueba.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nuevaprueba.R

class MiVista(miFila : View) : RecyclerView.ViewHolder(miFila) {
    var miTexto = miFila.findViewById<TextView>(R.id.txtFila)
}