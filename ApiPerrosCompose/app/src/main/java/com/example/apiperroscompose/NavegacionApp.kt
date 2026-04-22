package com.example.apiperroscompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navegacion() {
    val miNavController = rememberNavController()
    NavHost(miNavController, startDestination = "Pantalla inicial") {
        composable("Pantalla inicial") {listaPerros(miNavController)}
        composable("Detalle perro/{raza}") {
            val raza = it.arguments?.getString("raza") ?: ""
            detallePerro(raza, null, miNavController)
        }
        composable("Detalle perro/{raza}/{subraza}") {
            val raza = it.arguments?.getString("raza") ?: ""
            val subraza = it.arguments?.getString("subraza")
            detallePerro(raza, subraza, miNavController)
        }
    }
}