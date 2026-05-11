package com.example.proyectogex.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

// "http://10.239.251.245:8080" url para dispositivos externos
// "http://10.0.2.2:8080" url para dispositivos internos

@Composable
fun HomeScreen(url: String = "http://10.0.2.2:8080/") {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { content ->
            WebView(content).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },

        update = { webView ->
            if (webView.url != url) {
                webView.loadUrl(url)
            }
        }
    )
}