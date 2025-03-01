package com.mobile.tskpersonelteminapp.ui.theme

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun outlinedColor() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Transparent, // Odaklandığında arka plan rengi
    unfocusedContainerColor = Color.Transparent, // Odaklanmadığında arka plan rengi
    focusedIndicatorColor = line, // Odaklandığında çizgi rengi
    unfocusedIndicatorColor = Color.Gray, // Odaklanmadığında çizgi rengi
    cursorColor = line, // İmleç rengi
    focusedTextColor = line, // Kullanıcının yazdığı metin rengi (odaklanmışken)
    unfocusedTextColor = line, // Kullanıcının yazdığı metin rengi (odaklanmamışken)
    focusedLabelColor = line, // Label rengi (odaklanmışken)
    unfocusedLabelColor = line // Label rengi (odaklanmamışken)
)

@Composable
fun buttonColor()= ButtonDefaults.buttonColors(
    containerColor = toolbarColor,
    contentColor = offWhite
)