package com.mobile.tskpersonelteminapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AnnouncementDetailScreen(url : String) {
    // AndroidView ile WebView oluşturuyoruz
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true // JavaScript desteğini aç
            settings.cacheMode = WebSettings.LOAD_NO_CACHE // Önbelleği devre dışı bırak
            webViewClient = WebViewClient() // WebView içinde gezinme
            loadUrl(url) // URL'yi yükle
        }
    })
}