package com.mobile.tskpersonelteminapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.ui.components.BackHeader
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel) {
    val isChatNotificationEnabled by viewModel.isChatNotificationEnabled.collectAsState()
    val isFirstLaunch = viewModel.isFirstLaunch
    if (isFirstLaunch) {
        viewModel.setNotificationEnabled("chat", true)
        viewModel.setFirstLaunchDone()
    }
    BackHandler {
        navController.popBackStack()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Duyuru Bildirimleri")
            Spacer(modifier = Modifier.padding(8.dp))

            Switch(
                checked = isChatNotificationEnabled,
                onCheckedChange = { viewModel.setNotificationEnabled("chat", it) })
        }

        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick ={/*diğer ayaraları aç*/} ){
            Text("Diğer Ayarlar")
        }
    }

}