package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.theme.offWhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    val context = LocalContext.current

    // Animasyon efektleri
    LaunchedEffect(Unit) {
        delay(500) // 2 saniye sonra yönlendirme
        onNavigate() // Ana ekrana geçiş
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
            Icon(modifier = Modifier.size(96.dp),painter = painterResource(R.drawable.recruitment), contentDescription = "Logo", tint = Color.Black)
    }
}
