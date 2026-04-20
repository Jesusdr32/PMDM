package com.example.listacolores

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.listacolorescompose.ListaColoresViewModel

@Composable
fun listaFrutas() {
    val miLista = listOf("Apple", "Banana", "Orange", "Mango")
    LazyColumn {
        items(miLista) {item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun listaColores(navController: NavController) {
    val myViewModel: ListaColoresViewModel = viewModel()
    val misColores = myViewModel.listaNombres.collectAsState().value

    LazyColumn {
        items(misColores) {item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable{
                        navController.navigate("Detalle color/$item")
                    }
            )
        }
    }
}

@Composable
fun detalleColor(color: String, navController: NavController) {
    val myViewModel: ListaColoresViewModel = viewModel()
    myViewModel.detalleColor(color)
    val miColor = myViewModel.detalleColor.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center)) {
            Text(text = miColor.nombre)
            Text(text = miColor.codigoHex)
            Button(
                onClick = {
                    navController.navigate("Pantalla inicial")
                }) {
                Text(text = "Volver")
            }
        }
    }
}