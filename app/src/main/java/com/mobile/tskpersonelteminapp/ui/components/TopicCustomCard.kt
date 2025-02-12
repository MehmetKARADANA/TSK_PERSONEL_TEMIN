package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.mobile.tskpersonelteminapp.utils.formatTimestamp

@Composable
fun TopicCustomCard(content: String, date: Timestamp, userName: String, modifier: Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)) {
        Column (modifier=Modifier.fillMaxWidth()){
            Row(modifier=Modifier.padding(4.dp).fillMaxWidth()) { Text(text = content) }
               Column {   Text(text = formatTimestamp(date))}
                Column {  Text(text = userName)}
        }
    }
}