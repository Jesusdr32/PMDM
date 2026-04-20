package com.example.apiperroscompose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun listaPerros(navController: NavController) {
    val myViewModel: ListaPerrosViewModel = viewModel()
    val perros = myViewModel
}