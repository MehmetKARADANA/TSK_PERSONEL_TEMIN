package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.utils.navigateTo

enum class MenuItem(val itemName: String, val navDestinationScreen: DestinationScreen) {

    SETTINGS(itemName = "Ayarlar", DestinationScreen.Settings),
    PROFILE(itemName = "Profilim", DestinationScreen.Profile),
    ABOUT(itemName = "Hakkımızda", DestinationScreen.AboutUs),
    SUGGESTION(itemName = "Görüş/Öneri", DestinationScreen.Suggestion)
}


@Composable
fun MenuScreen(navController: NavController) {
    Column (modifier = Modifier
        .fillMaxSize()) {
        Column (modifier = Modifier.fillMaxWidth().weight(1f).padding(4.dp)){
            for (item in MenuItem.entries) {
                Text(text = item.itemName, modifier = Modifier.clickable {
                  navigateTo(route=item.navDestinationScreen.route, navController = navController)
                })
            }
        }
        BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.MENU, navController = navController)
    }
}