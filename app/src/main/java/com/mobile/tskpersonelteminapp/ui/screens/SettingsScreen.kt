package com.mobile.tskpersonelteminapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.ui.components.BackHeader
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.ui.theme.primaryColor
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.viewmodels.NotificationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController, settingsViewModel : SettingsViewModel,notificationViewModel: NotificationViewModel) {

    val errorMessage by notificationViewModel.errorMessage
    val context = LocalContext.current
    // Hata mesajını dinle ve göster
    ObserveErrorMessage(errorMessage)
    val isChatNotificationEnabled by settingsViewModel .isChatNotificationEnabled.collectAsState()
    val isFirstLaunch = settingsViewModel .isFirstLaunch
    if (isFirstLaunch) {
        settingsViewModel .setNotificationEnabled("chat", true)
        settingsViewModel .setFirstLaunchDone()
    }
    BackHandler {
        navController.popBackStack()
    }

Column( modifier = Modifier
    .fillMaxSize()
    .background(primaryColor)) {
    BackHeader(onBackClicked = {
        navController.popBackStack()
    },"Bildirim Ayarları")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Duyuru Bildirimleri")
            Spacer(modifier = Modifier.padding(8.dp))

            Switch(
                checked = isChatNotificationEnabled,
                onCheckedChange = { settingsViewModel.setNotificationEnabled("chat", it) })
        }

        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {notificationViewModel.openNotificationSettings(context) }) {
            Text("Bildirim Ayarları")
        }
    }
}
}