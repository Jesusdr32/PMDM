package com.example.proyectogex.navigation

object Routes {
    const val LOGIN = "login"
    const val MAIN = "main"

    const val HOME = "home"
    const val PRODUCTS = "products"
    const val CART = "cart"

    // Ruta con parámetro para el detalle de un producto.
    // Se navega llamando a productDetail(id) y se declara con PRODUCT_DETAIL.
    const val PRODUCT_DETAIL = "product_detail/{productId}"
    fun productDetail(productId: Long) = "product_detail/$productId"
}
