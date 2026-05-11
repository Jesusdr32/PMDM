package com.example.proyectogex.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.proyectogex.viewmodel.CartViewModel
import com.example.proyectogex.viewmodel.ProductsViewModel

@Composable
fun ProductDetailScreen(
    productId: Long,
    navController: NavHostController,
    productsViewModel: ProductsViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    // En cuanto se monta la pantalla, pedimos los datos del producto y
    // recargamos el carrito para saber cuántas unidades ya hay añadidas.
    LaunchedEffect(productId) {
        productsViewModel.loadProductById(productId)
        cartViewModel.loadCart()
    }

    val product = productsViewModel.selectedProduct
    val isLoading = productsViewModel.isLoading

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }
            Text(
                text = "Detalle del producto",
                style = MaterialTheme.typography.titleLarge
            )
        }

        if (isLoading || product == null) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            val totalStock = product.productStock

            val alreadyInCart = cartViewModel.cartResponse
                ?.products
                ?.firstOrNull { it.productId == product.productId }
                ?.units
                ?: 0

            val availableStock = totalStock?.let { (it - alreadyInCart).coerceAtLeast(0) }

            val maxQuantity = availableStock ?: Int.MAX_VALUE
            val isOutOfStock = availableStock != null && availableStock <= 0

            var quantity by remember(productId) { mutableIntStateOf(1) }

            LaunchedEffect(maxQuantity) {
                if (quantity > maxQuantity) quantity = maxQuantity.coerceAtLeast(1)
            }

            val discount = product.productDiscount ?: 0
            val finalPrice = if (discount > 0) {
                product.productPrice * (1 - discount / 100.0)
            } else {
                product.productPrice
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(
                        text = product.productName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = product.productDescription,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (discount > 0) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${"%.2f".format(product.productPrice)} €",
                                style = MaterialTheme.typography.bodyMedium,
                                textDecoration = TextDecoration.LineThrough,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "${"%.2f".format(finalPrice)} €",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "-${discount}%",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    } else {
                        Text(
                            text = "${"%.2f".format(product.productPrice)} €",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = when {
                            totalStock == null -> "Stock no disponible"
                            availableStock == null -> "Stock no disponible"
                            availableStock <= 0 && alreadyInCart > 0 ->
                                "Has añadido todas las unidades disponibles"

                            availableStock <= 0 -> "Agotado"
                            availableStock < 5 ->
                                "¡Solo quedan $availableStock unidades disponibles!"

                            else -> "Disponibles: $availableStock unidades"
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isOutOfStock) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.onSurface
                    )

                    if (alreadyInCart > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "En tu carrito: $alreadyInCart",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Selector de cantidad
                    Text(
                        text = "Cantidad",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { if (quantity > 1) quantity-- },
                            enabled = quantity > 1 && !isOutOfStock
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Restar uno"
                            )
                        }

                        Text(
                            text = quantity.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        IconButton(
                            onClick = { if (quantity < maxQuantity) quantity++ },
                            enabled = quantity < maxQuantity && !isOutOfStock
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Sumar uno"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    onClick = {
                        cartViewModel.addProduct(product.productId, quantity)
                        quantity = 1
                    },
                    enabled = !isOutOfStock,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = if (isOutOfStock) "Sin stock" else "Añadir al carrito"
                    )
                }
            }
        }
    }
}
