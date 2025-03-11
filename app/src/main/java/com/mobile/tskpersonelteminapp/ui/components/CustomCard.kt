package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.offWhite


@Composable
fun CustomCard(title: String, date: String, modifier: Modifier) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .padding(4.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)

    ) {
        Column(modifier = Modifier
            .background(color = offWhite)
            .padding(4.dp)) {
            Text(
                text = title,
                modifier = Modifier.padding(4.dp),
                fontFamily = FontFamily.Serif,
                color = line
            )
            Divider(color = Color.Gray)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified,
                    contentDescription = "Calendar"
                )
                Text(
                    text = date,
                    modifier = Modifier.padding(4.dp),
                    fontFamily = FontFamily.Serif,
                    color = line
                )
            }
        }
    }

}