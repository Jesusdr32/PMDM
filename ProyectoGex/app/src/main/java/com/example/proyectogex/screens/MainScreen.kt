package com.example.proyectogex.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectogex.navigation.Routes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

@Composable
fun MainScreen(navController: NavHostController) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.HOME) {
//                HomeScreen()
            }

            composable(Routes.PRODUCTS) {
//                ProductsScreen(
//                    onProductClick = { productId ->
//                        //  abrir detalle o añadir al carrito
//                    }
//                )
            }

            composable(Routes.CART) {
//                CartScreen()
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Routes.HOME)
            },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Routes.PRODUCTS)
            },
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            label = { Text("Productos") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Routes.CART)
            },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text("Carrito") }
        )
    }
}