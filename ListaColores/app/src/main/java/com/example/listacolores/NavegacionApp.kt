package com.example.listacolores

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navegacion() {
    val miNavController = rememberNavController()
    NavHost(miNavController, startDestination = "Pantalla inicial") {
        composable("Pantalla inicial") {listaColores(miNavController)}
        composable("Detalle color/{codigo}") {
            val miCodigo = it.arguments?.getString("codigo") ?: ""
            detalleColor(miCodigo, miNavController)}
    }
}