package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.ui.components.ComminityCustomCard
import com.mobile.tskpersonelteminapp.ui.components.CommonProgressBar
import com.mobile.tskpersonelteminapp.ui.components.EmptyHeader
import com.mobile.tskpersonelteminapp.ui.theme.primaryColor
import com.mobile.tskpersonelteminapp.ui.theme.toolbarColor
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.ComminityViewModel

@Composable
fun ComminityScreen(navController: NavController, viewModel: ComminityViewModel) {

    val errorMessageCv by viewModel.errorMessage

    ObserveErrorMessage(errorMessageCv)

    val inProcess = viewModel.inProcess.value
    val errorMessage = viewModel.errorMessage.value
    val themes = viewModel.themes.value
    Column(modifier = Modifier.fillMaxSize().background(color = primaryColor)) {
        EmptyHeader("Topluluk")
        if (inProcess) {
            CommonProgressBar()
        } else if (errorMessage != null) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Error : $errorMessage")
            }
        } else {
            if (themes.isNotEmpty()) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)) {
                    items(themes) {
                     /*   Text(text = it.theme, modifier = Modifier.clickable {
                           navigateTo(navController,DestinationScreen.Topics.createRoute(themeId = it.themeId!!))
                        })
                        Text(text = "-------")*/
                        ComminityCustomCard(content = it.theme, modifier = Modifier.clickable {
                            navigateTo(navController,DestinationScreen.Topics.createRoute(themeId = it.themeId!!))
                        })
                    }
                }
            }else{
                Box(modifier = Modifier.fillMaxWidth().weight(1f))
            }
        }
        BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.COMMINITY, navController)
    }
}
