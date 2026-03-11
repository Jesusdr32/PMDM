package com.example.recyclerview_apidog.recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_apidog.R

class MyVista(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val imV1 : ImageView = itemView.findViewById(R.id.imV1)
}