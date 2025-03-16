package com.mobile.tskpersonelteminapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AnnouncementDetailScreen(url : String) {
    // AndroidView ile WebView oluşturuyoruz

    // Bağlantı kontrolü
    if (!isNetworkAvailable()) {
        Toast.makeText(LocalContext.current, "İnternet Bağlantısı Yok!", Toast.LENGTH_SHORT).show()
    }
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true // JavaScript desteğini aç
            settings.cacheMode = WebSettings.LOAD_NO_CACHE // Önbelleği devre dışı bırak
            webViewClient = WebViewClient() // WebView içinde gezinme
            loadUrl(url) // URL'yi yükle
        }
    })
}


@Composable
@SuppressLint("ServiceCast")
fun isNetworkAvailable(): Boolean {
    val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork?.isConnected == true
}