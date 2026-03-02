package com.example.recyclerviewcolores.model

class ColoresModel {

    var colores = mutableListOf(
        Color("Rojo", "#FF0000"),
        Color("Verde", "#00FF00"),
        Color("Azul", "#0000FF"),
        Color("Amarillo", "#FFFF00"),
        Color("Naranja", "#FFA500"),
        Color("Morado", "#800080"),
        Color("Rosa", "#FFC0CB"),
        Color("Cian", "#00FFFF"),
        Color("Magenta", "#FF00FF"),
        Color("Gris", "#808080"),
        Color("Negro", "#000000"),
        Color("Lavanda", "#E6E6FA"),
        Color("Marrón", "#8B4513"),
        Color("Turquesa", "#40E0D0"),
        Color("Azul marino", "#000080")
    )
    public suspend fun retornarLista() : Datos {
        return Datos("ok", colores)
    }
}