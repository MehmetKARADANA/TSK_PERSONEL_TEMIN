package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.tskpersoneltemin.ui.components.BottomNavigationMenu
import com.mobile.tskpersoneltemin.ui.components.BottomNavigationMenuItem

@Composable
fun ScreenTest(navController: NavController) {
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text = "Baslamak lazim", fontSize = 22.sp, modifier = Modifier.weight(1f))
        BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.MENU, navController = navController)
    }

}