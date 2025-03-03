package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.offWhite
import com.mobile.tskpersonelteminapp.utils.formatTimestamp

@Composable
fun TopicCustomCard(
    content: String,
    date: Timestamp,
    userName: String,
    pinned: Boolean,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(offWhite)
            .padding(4.dp)) {
            Row(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (pinned) {
                    Icon(
                        painter = painterResource(R.drawable.pin),
                        contentDescription = "Pinned",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Unspecified
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) { Text(text = "\uD83D\uDCAC $content", color = line) }
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column { Text(text = formatTimestamp(date),color = line) }
                Column { Text(text = userName,color = line) }
            }
        }
    }
}