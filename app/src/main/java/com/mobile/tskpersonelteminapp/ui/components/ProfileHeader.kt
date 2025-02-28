package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.tskpersonelteminapp.ui.theme.offWhite
import com.mobile.tskpersonelteminapp.ui.theme.toolbarColor


@Composable
fun ProfileHeader(onBackClicked: () -> Unit, onLogoutClicked: () -> Unit, headerText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = toolbarColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(30.dp)
                .clickable {
                    onBackClicked.invoke()
                }, tint = offWhite
        )//fontweigth ??
        Text(
            text = headerText,
            fontWeight = FontWeight.W500,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
            color = offWhite
        )
        Icon(
            Icons.Default.ExitToApp, contentDescription = "Exit",
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .size(30.dp)
                .clickable {
                    onLogoutClicked.invoke()
                }, tint = offWhite)

    }
}