package com.mobile.tskpersonelteminapp.ui.theme

import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val primaryColor = Color(0xFFF5F5F5)
val toolbarColor = Color(0xFFB03A2E)//Color(0xFF1CA9C9)

val line=Color(0xFF2C2C2C)//line gray

val background=Color(0xFFE8E4D7)
val offWhite=Color(0xFFFAF9F6)//card color

val cream=Color(0xFFD7D0C8)

val gradientBrush = Brush.linearGradient(
    colors = listOf(Color(0xFF2196F3), Color(0xFF9C27B0)), // Mavi → Mor
    start = Offset(0f, 0f),
    end = Offset(1000f, 1000f)
)

val gradientBrushTurk = Brush.linearGradient(
    colors = listOf(Color(0xFF0093DD), Color(0xFFE30A18)), // Türkistan Mavisi → Kırmızı
    start = Offset(0f, 0f),
    end = Offset(1000f, 1000f)
)

