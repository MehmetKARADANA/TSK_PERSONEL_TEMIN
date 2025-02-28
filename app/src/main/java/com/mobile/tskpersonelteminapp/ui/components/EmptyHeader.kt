package com.mobile.tskpersonelteminapp.ui.components

import android.app.Activity
import android.app.StatusBarManager
import android.os.Build
import android.view.WindowInsets
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.offWhite
import com.mobile.tskpersonelteminapp.ui.theme.toolbarColor


@Composable
fun EmptyHeader(headerText: String) {
    val statusBarColor = toolbarColor
    val activity = LocalActivity.current // Activity'ye erişim
    activity?.window?.statusBarColor = statusBarColor.toArgb() // Rengi değiştiriyoruz
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(8.dp)
            .background(color = toolbarColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = headerText, fontWeight = FontWeight.W500,
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
            color = offWhite//line
        )
    }

}
