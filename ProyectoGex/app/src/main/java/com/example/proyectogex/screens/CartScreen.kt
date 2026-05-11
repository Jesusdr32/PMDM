package com.example.proyectogex.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectogex.data.dto.CartItemDto
import com.example.proyectogex.viewmodel.CartViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel = viewModel()
) {

    val cart = cartViewModel.cartResponse
    val isLoading = cartViewModel.isLoading

    var selectedProductId by remember { mutableStateOf<Long?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Column
        }

        if (cart?.products.isNullOrEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Carrito vacío",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "No se encuentran productos en el carrito",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            return@Column
        }

        LazyColumn(modifier = Modifier.weight(1f)) {

            items(cart.products) { item ->

                val isSelected = selectedProductId == item.productId

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        else
                            MaterialTheme.colorScheme.surface
                    ),
                    onClick = {
                        selectedProductId = item.productId
                        showDialog = true
                    }
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Text(
                            text = item.productName,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text("Unidades: ${item.units}")
                        Text("Precio unitario: ${item.unitPrice} €")
                        Text("Total: ${item.totalPrice} €")
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(12.dp)) {

            Text("Productos distintos: ${cart.distinctProducts}")
            Text("Total unidades: ${cart.totalUnits}")
            Text("Precio total: ${cart.totalPrice} €")

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    cartViewModel.clearCart()
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Vaciar carrito")
            }
        }
    }

    if (showDialog && selectedProductId != null) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
                selectedProductId = null
            },

            title = {
                Text("Eliminar producto")
            },

            text = {
                Text("¿Seguro que quieres eliminar este producto del carrito?")
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        selectedProductId?.let { id ->
                            cartViewModel.removeProduct(id)
                        }
                        showDialog = false
                        selectedProductId = null
                    }
                ) {
                    Text("Sí")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        selectedProductId = null
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}