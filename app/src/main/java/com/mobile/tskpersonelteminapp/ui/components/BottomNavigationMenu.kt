package com.mobile.tskpersonelteminapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.theme.cream
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.offWhite
import com.mobile.tskpersonelteminapp.ui.theme.toolbarColor
import com.mobile.tskpersonelteminapp.utils.navigateTo

enum class BottomNavigationMenuItem(val image: Int, val navDestinationScreen: DestinationScreen) {
    ANNOUNCEMENT(R.drawable.announcement, DestinationScreen.Announcements),
    RECRUITMENT(R.drawable.recruitment, DestinationScreen.CurrentRecruitment),
    COMMINITY(R.drawable.comminity, DestinationScreen.Community),
    MENU(R.drawable.menu, DestinationScreen.Menu)
}

@Composable
fun BottomNavigationMenu(selectedItem: BottomNavigationMenuItem, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 4.dp)) {
      //  Divider(color = Color.Gray)
        Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
           // .padding(top = 4.dp)
            .background(/*color = Color.White*/toolbarColor)
    ) {
        for (item in BottomNavigationMenuItem.values()) {
            Image(
                painter = painterResource(item.image),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(navController, item.navDestinationScreen.route)
                    },
                colorFilter = if (item == selectedItem)
                    ColorFilter.tint(line)
                else
                    ColorFilter.tint(cream)
            )
        }
    }}
}