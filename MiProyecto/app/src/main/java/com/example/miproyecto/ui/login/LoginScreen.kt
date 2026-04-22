package com.example.miproyecto.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(nav: NavController, vm: LoginViewModel = viewModel()) {
    Column {
        TextField(vm.username, { vm.username = it }, label = { Text("Usuario") })
        TextField(vm.password, { vm.password = it }, label = { Text("Contraseña") })

        Button(onClick = {
            vm.login {
                nav.navigate("main")
            }
        }) {
            Text("Login")
        }

        vm.error?.let { Text(it) }
    }
}