package com.example.miproyecto.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.miproyecto.domain.SessionManager
import com.example.miproyecto.ui.home.HomeScreen
import com.example.miproyecto.ui.products.ProductsScreen

@Composable
fun MainScreen(navController: NavController) {
    var selected by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${SessionManager.username}") },
                actions = {
                    IconButton(onClick = {
                        SessionManager.token = null
                        navController.navigate("login")
                    }) {
                        Icon(Icons.Default.ExitToApp, null)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                listOf("Inicio", "Productos", "Carrito").forEachIndexed { i, label ->
                    NAvigationBarItem(
                        selected = selected == i,
                        onClick = { selected = i },
                        icon = { Icon(Icons.Default.Home, null) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when (selected) {
                0 -> HomeScreen()
                1 -> ProductsScreen()
                2 -> CartScreen()
            }
        }
    }
}