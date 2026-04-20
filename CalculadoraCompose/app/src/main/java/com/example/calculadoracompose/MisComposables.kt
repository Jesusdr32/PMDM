package com.example.calculadoracompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment;
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun calculadora() {
    val miViewModel : MiViewModel = viewModel()
    var num1 by rememberSaveable { mutableStateOf("0.0") }
    var num2 by rememberSaveable { mutableStateOf("0.0") }
    var total = miViewModel.cont.collectAsState().value

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
        .offset(y = 30.dp)) {
        Column(modifier = Modifier
//            .background(Color.Magenta)
            .fillMaxSize()
            .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Row(
//                modifier = Modifier.background(Color.Red),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Numero 1: ",
                    color = Color.Black
                )
                TextField(
                    value = num1,
                    onValueChange = {
                        num1 = it
                    },
                    label = { Text("Inserte un número") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            Row(
//                modifier = Modifier.background(Color.Cyan),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Numero 2: ",
                    color = Color.Black
                )
                TextField(
                    value = num2,
                    onValueChange = {
                        num2 = it
                    },
                    label = { Text("Inserte un número") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
        }
        Row(modifier = Modifier
//            .background(Color.Green)
            .fillMaxWidth()
            .align(Alignment.Center)
            .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    miViewModel.sumar(num1.toDouble(), num2.toDouble())
            }) {
                Text(text = "+")
            }
            Button(
                onClick = {
                    miViewModel.restar(num1.toDouble(), num2.toDouble())
            }) {
                Text(text = "-")
            }
            Button(
                onClick = {
                    miViewModel.multiplicar(num1.toDouble(), num2.toDouble())
            }) {
                Text(text = "*")
            }
            Button(
                onClick = {
                    miViewModel.dividir(num1.toDouble(), num2.toDouble())
            }) {
                Text(text = "/")
            }
        }
        Column(modifier = Modifier
//            .background(Color.Blue)
            .height(150.dp)
            .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = total.toString(),
                fontSize = 40.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}