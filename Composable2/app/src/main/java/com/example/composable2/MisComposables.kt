package com.example.composable2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun holaComposable() {
    var numeroSumar by rememberSaveable { mutableStateOf("0") }
    var totalAcumulado by rememberSaveable { mutableStateOf("0") }

    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Yellow)) {
        Column(modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
//            .height(100.dp)
            .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = totalAcumulado,
                color = Color.Red,
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxWidth(),
//                    .height(50.dp),
                textAlign = TextAlign.Center
            )
            TextField(
                value = numeroSumar,
                onValueChange = {
                    Log.e("COMPOSE", "Estoy tecleando")
                    numeroSumar = it
                },
                label = { Text("Inserte un número") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(onClick = {
                var suma = totalAcumulado.toInt()
                suma += numeroSumar.toInt()
                totalAcumulado = suma.toString()
            }) {
                Text(text = "Sumar")
            }
        }
    }
}


@Composable
fun holaComposableMVVM() {
    val miViewModel : MiViewModel = viewModel()
    var numeroSumar by rememberSaveable { mutableStateOf("0") }
    var totalAcumulado = miViewModel.cont.collectAsState().value

    Log.e("COMPOSE3", totalAcumulado.toString())

    Box(modifier = Modifier.fillMaxSize()
        .background(Color.Yellow)) {
        Column(modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
//            .height(100.dp)
            .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = totalAcumulado.toString(),
                color = Color.Red,
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxWidth(),
//                    .height(50.dp),
                textAlign = TextAlign.Center
            )
            TextField(
                value = numeroSumar,
                onValueChange = {
                    numeroSumar = it
                },
                label = { Text("Inserte un número") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(onClick = {
                Log.e("COMPOSE4", "Estoy en el onclick")
                miViewModel.sumar(numeroSumar.toInt())
//                numeroSumar = 0.toString()
            }) {
                Text(text = "Sumar")
            }
        }
    }
}
