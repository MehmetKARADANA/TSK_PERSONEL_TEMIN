package com.mobile.tskpersoneltemin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.tskpersoneltemin.utils.navigateTo
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.R

enum class BottomNavigationMenuItem(val image: Int, val navDestinationScreen: DestinationScreen) {
    ANNOUNCEMENT(R.drawable.announcement, DestinationScreen.announcements),
    RECRUITMENT(R.drawable.recruitment,DestinationScreen.current_recruitment),
    COMMINITY(R.drawable.comminity, DestinationScreen.community),
    MENU(R.drawable.menu, DestinationScreen.menu)
}

@Composable
fun BottomNavigationMenu(selectedItem: BottomNavigationMenuItem, navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 4.dp)
        .background(Color.White)) {
        for (item in BottomNavigationMenuItem.values()){
            Image(painter = painterResource(item.image), contentDescription = "", modifier = Modifier.size(40.dp).padding(4.dp).weight(1f).clickable {
                navigateTo(navController,item.navDestinationScreen.route)
            }, colorFilter = if(item == selectedItem)
            ColorFilter.tint(Color.Black)
            else
            ColorFilter.tint(Color.Gray))
        }
    }
}