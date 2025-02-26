package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.background
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
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.ui.components.ComminityCustomCard
import com.mobile.tskpersonelteminapp.ui.components.EmptyHeader
import com.mobile.tskpersonelteminapp.ui.components.MenuCustomCard
import com.mobile.tskpersonelteminapp.ui.theme.primaryColor
import com.mobile.tskpersonelteminapp.utils.navigateTo

enum class MenuItem(val itemName: String, val navDestinationScreen: DestinationScreen,val image : Int) {

    SETTINGS(itemName = "Ayarlar", DestinationScreen.Settings,R.drawable.settings),
    PROFILE(itemName = "Profilim", DestinationScreen.Profile,R.drawable.user),
    ABOUT(itemName = "Hakkımızda", DestinationScreen.AboutUs,R.drawable.info),
    SUGGESTION(itemName = "Görüş/Öneri", DestinationScreen.Suggestion,R.drawable.edit)
}


@Composable
fun MenuScreen(navController: NavController) {
    Column (modifier = Modifier
        .fillMaxSize().background(primaryColor)) {
        EmptyHeader("Menü")
        Column (modifier = Modifier.fillMaxWidth().weight(1f).padding(4.dp)){
            for (item in MenuItem.entries) {
                MenuCustomCard(content = item.itemName, image = item.image,modifier = Modifier.clickable {
                        navigateTo(route=item.navDestinationScreen.route, navController = navController)
                    })
            }
        }
        BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.MENU, navController = navController)
    }
}