package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomCard(title: String, date: String,modifier: Modifier) {
    Card (modifier = modifier.wrapContentHeight().padding(4.dp).fillMaxWidth()){
        Column (modifier=Modifier.padding(4.dp)){

                Text(text = title,modifier=Modifier.padding(4.dp))
                Text(text = date,modifier=Modifier.padding(4.dp))

        }
    }
}