package com.example.proyectogex.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.proyectogex.data.dto.ProductDto
import com.example.proyectogex.navigation.Routes
import com.example.proyectogex.viewmodel.CategoriesViewModel
import com.example.proyectogex.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    navController: NavHostController,
    productsViewModel: ProductsViewModel = viewModel(),
    categoriesViewModel: CategoriesViewModel = viewModel()
) {
    val products = productsViewModel.products
    val categories = categoriesViewModel.categories
    val isLoading = productsViewModel.isLoading
    val canLoadMore = productsViewModel.canLoadMore

    var selectedCategoryId by remember { mutableStateOf<Long?>(null) }

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastIndex ->
            val totalItems = listState.layoutInfo.totalItemsCount

            if (lastIndex != null && lastIndex >= totalItems -1 && canLoadMore && !isLoading) {
                productsViewModel.loadNextPage()
            }
        }
    }

    LaunchedEffect(Unit) {
        productsViewModel.loadAllProducts()
        categoriesViewModel.loadCategories()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(modifier = Modifier.padding(8.dp)) {
            item {
                FilterChip(
                    selected = selectedCategoryId == null,
                    onClick = {
                        selectedCategoryId = null
                        productsViewModel.loadAllProducts()
                    },
                    label = { Text("Todos") }
                )
            }
            items(categories) { category ->
                FilterChip(
                    selected = selectedCategoryId == category.id,
                    onClick = {
                        selectedCategoryId = category.id
                        productsViewModel.loadProductsByCategory(category.id)
                    },
                    label = { Text(category.name) }
                )
            }
        }

        if (isLoading && products.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f)
            ) {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        onClick = {
                            navController.navigate(Routes.productDetail(product.productId))
                        }
                    )
                }

                if (canLoadMore) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: ProductDto,
    onClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = product.productName,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${product.productPrice} €",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onClick) {
                Text("Detalle de compra")
            }
        }
    }
}
