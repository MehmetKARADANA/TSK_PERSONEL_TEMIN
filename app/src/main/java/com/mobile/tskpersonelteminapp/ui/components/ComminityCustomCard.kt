package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.offWhite

@Composable
fun ComminityCustomCard(content: String, modifier: Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)) {
        Column(modifier=Modifier.fillMaxWidth().background(offWhite).padding(8.dp)) {  Text(text = "\uD83D\uDC65 "+content, color = line)
        }
    }
}