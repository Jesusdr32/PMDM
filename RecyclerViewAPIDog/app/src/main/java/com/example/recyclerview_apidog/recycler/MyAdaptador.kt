package com.example.recyclerview_apidog.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview_apidog.R
import com.example.recyclerview_apidog.model.DogRespuesta

class MyAdaptador(private var misFotos : DogRespuesta) : RecyclerView.Adapter<MyVista>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)
        return MyVista(view)
    }

    override fun onBindViewHolder(holder: MyVista, position: Int) {
        val urlFoto : String = misFotos.message!![position]

        Glide.with(holder.itemView.context).load(urlFoto).into(holder.imV1)
    }

    override fun getItemCount() : Int {
        return misFotos.message?.size ?: 0
    }
}