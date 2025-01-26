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
import com.mobile.tskpersonelteminapp.ui.screens.AnnouncementDetailScreen
import com.mobile.tskpersonelteminapp.ui.screens.AnnouncementsScreen
import com.mobile.tskpersonelteminapp.ui.screens.ComminityScreen
import com.mobile.tskpersonelteminapp.ui.screens.MenuScreen
import com.mobile.tskpersonelteminapp.ui.screens.RecruitmentScreen
import com.mobile.tskpersonelteminapp.ui.theme.TskPersonelTeminAppTheme
import com.mobile.tskpersonelteminapp.viewmodels.AnnouncementsViewModels
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


sealed class DestinationScreen(var route: String) {
    object current_recruitment : DestinationScreen("current_recruitment")
    object announcements : DestinationScreen("announcements")
    object announcement_detail : DestinationScreen("announcement_detail/{detail_url}") {

        fun createRoute(detail_url: String): String {
            val encodedUrl = URLEncoder.encode(detail_url, StandardCharsets.UTF_8.toString())
            return "announcement_detail/$encodedUrl"
        }
    }

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

            composable(DestinationScreen.current_recruitment.route) {
                // screen
                RecruitmentScreen(navController)
            }

            composable(DestinationScreen.menu.route) {
                // screen
                MenuScreen(navController)
            }

            composable(DestinationScreen.community.route) {
                ComminityScreen(navController)
            }

            composable(DestinationScreen.announcement_detail.route) {
                val detail_url = it.arguments?.getString("detail_url")
                detail_url?.let {
                    AnnouncementDetailScreen(detail_url)
                }

            }
        }
    }
}

@HiltAndroidApp
class LcApplication : Application() {

}