package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.ui.components.ComminityHeader
import com.mobile.tskpersonelteminapp.utils.navigateTo

@Composable
fun ComminityScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        ComminityHeader("Comminity", onBackClicked = {

        }, onAccountClicked = {
            //burrayı user varmı kontrol fonksiyonu eklencek navigateide onun içine ekleyeceğim
            navigateTo(navController,DestinationScreen.Profile.route)
        }, onAddClicked = {

        })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Comminity")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.COMMINITY, navController = navController)
    }


}