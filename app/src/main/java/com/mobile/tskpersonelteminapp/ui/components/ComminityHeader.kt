package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.offWhite
import com.mobile.tskpersonelteminapp.ui.theme.toolbarColor

//three click functions and title
@Composable
fun ComminityHeader(
    headerText: String,
    onBackClicked: () -> Unit,
    onAccountClicked: () -> Unit,
    onAddClicked: () -> Unit
) {
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
                .shadow(24.dp, shape = RoundedCornerShape(12.dp), clip = false)
                .clickable {
                    onBackClicked.invoke()
                }, tint = offWhite
        )//fontweigth ??
        Text(
            text = headerText,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily.Serif,
            color = offWhite,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .shadow(24.dp, shape = RoundedCornerShape(12.dp), clip = false)
        )
        Column(modifier = Modifier.padding(end = 8.dp)) {
            Row {
                Icon(Icons.Default.Add, "Add", modifier = Modifier
                    .size(30.dp)
                    .padding(end = 4.dp)
                    .shadow(24.dp, shape = RoundedCornerShape(12.dp), clip = false)
                    .clickable {
                        onAddClicked.invoke()
                    }, tint = offWhite
                )
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    tint = offWhite,
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(24.dp, shape = RoundedCornerShape(12.dp), clip = false)
                        .clickable {
                            onAccountClicked.invoke()
                        }
                )
            }
        }

    }
}