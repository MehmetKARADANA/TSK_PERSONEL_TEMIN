package com.mobile.tskpersonelteminapp

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobile.tskpersonelteminapp.ui.screens.AnnouncementsScreen
import com.mobile.tskpersonelteminapp.ui.screens.ScreenTest
import com.mobile.tskpersonelteminapp.ui.theme.TskPersonelTeminAppTheme
import com.mobile.tskpersonelteminapp.viewmodels.AnnouncementsViewModels
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


sealed class DestinationScreen(var route: String) {
    object current_supplies : DestinationScreen("current_supplies")
    object announcements : DestinationScreen("announcements")
    object menu : DestinationScreen("menu")
    object community : DestinationScreen("community")
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TskPersonelTeminAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }

        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        val announcementsViewModels = hiltViewModel<AnnouncementsViewModels>()

        NavHost(navController = navController, startDestination = DestinationScreen.menu.route) {
            composable(DestinationScreen.announcements.route) {
                //announcement screen
                //   screentest()
                AnnouncementsScreen(navController, announcementsViewModels)
            }

            composable(DestinationScreen.current_supplies.route) {
                // screen

            }

            composable(DestinationScreen.menu.route) {
                // screen
                ScreenTest(navController)
            }

            composable(DestinationScreen.community.route) {
                // screen
            }
        }
    }
}

@HiltAndroidApp
class LcApplication : Application() {

}