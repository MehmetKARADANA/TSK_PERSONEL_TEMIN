package com.mobile.tskpersonelteminapp

import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobile.tskpersonelteminapp.ui.screens.AboutUsScreen
import com.mobile.tskpersonelteminapp.ui.screens.AnnouncementDetailScreen
import com.mobile.tskpersonelteminapp.ui.screens.AnnouncementsScreen
import com.mobile.tskpersonelteminapp.ui.screens.CommentScreen
import com.mobile.tskpersonelteminapp.ui.screens.ComminityAdminScreen
import com.mobile.tskpersonelteminapp.ui.screens.ComminityScreen
import com.mobile.tskpersonelteminapp.ui.screens.LoginScreen
import com.mobile.tskpersonelteminapp.ui.screens.MenuScreen
import com.mobile.tskpersonelteminapp.ui.screens.NotificationScreen
import com.mobile.tskpersonelteminapp.ui.screens.ProfileScreen
import com.mobile.tskpersonelteminapp.ui.screens.RecruitmentScreen
import com.mobile.tskpersonelteminapp.ui.screens.SettingsScreen
import com.mobile.tskpersonelteminapp.ui.screens.SignUpScreen
import com.mobile.tskpersonelteminapp.ui.screens.SuggestionScreen
import com.mobile.tskpersonelteminapp.ui.screens.TopicsScreen
import com.mobile.tskpersonelteminapp.ui.theme.TskPersonelTeminAppTheme
import com.mobile.tskpersonelteminapp.viewmodels.AnnouncementsViewModel
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.ComminityViewModel
import com.mobile.tskpersonelteminapp.viewmodels.NotificationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.RecruitmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import android.Manifest
import com.mobile.tskpersonelteminapp.viewmodels.SettingsViewModel
import com.mobile.tskpersonelteminapp.viewmodels.SuggestionViewModel


sealed class DestinationScreen(var route: String) {
    object CurrentRecruitment : DestinationScreen("current_recruitment")
    object RecruitmentDetail : DestinationScreen("recruitment_detail/{detail_url}") {
        fun createRoute(detail_url: String): String {
            val encodeUrl = URLEncoder.encode(detail_url, StandardCharsets.UTF_8.toString())
            return "recruitment_detail/$encodeUrl"
        }
    }

    object Announcements : DestinationScreen("announcements")
    object AnnouncementDetail : DestinationScreen("announcement_detail/{detail_url}") {

        fun createRoute(detail_url: String): String {
            val encodedUrl = URLEncoder.encode(detail_url, StandardCharsets.UTF_8.toString())
            return "announcement_detail/$encodedUrl"
        }
    }

    object Menu : DestinationScreen("menu")
    object Community : DestinationScreen("community")

    object CommunityAdmin : DestinationScreen("communityAdmin")
    object SignUp : DestinationScreen("signup")
    object Login : DestinationScreen("login")

    object Topics : DestinationScreen("topics/{themeId}") {
        fun createRoute(themeId: String) = "topics/$themeId"
    }

    object Comments : DestinationScreen("comments/{themeId}/{topicId}") {
        fun createRoute(themeId: String, topicId: String) = "comments/$themeId/$topicId"
    }


    object Settings : DestinationScreen("settings")
    object Profile : DestinationScreen("profile")
    object AboutUs : DestinationScreen("aboutUs")
    object Suggestion : DestinationScreen("suggestion")

    object TestNotification : DestinationScreen("notification")


}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermission()
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

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermissions = ContextCompat.checkSelfPermission(
                this.applicationContext, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermissions) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()

        val announcementsViewModel = hiltViewModel<AnnouncementsViewModel>()
        val recruitmentViewModel = hiltViewModel<RecruitmentViewModel>()
        val authenticationViewModel = hiltViewModel<AuthenticationViewModel>()
        val comminityViewModel = hiltViewModel<ComminityViewModel>()
        val notificationViewModel: NotificationViewModel by viewModels()
        val settingsViewModel: SettingsViewModel by viewModels()
        val suggestionViewModel = hiltViewModel<SuggestionViewModel>()



        NavHost(
            navController = navController,
            startDestination = DestinationScreen.Announcements.route
        ) {
            composable(DestinationScreen.Announcements.route) {
                //announcement screen
                //   screentest()
                AnnouncementsScreen(navController, announcementsViewModel)
            }
            composable(DestinationScreen.TestNotification.route) {
                NotificationScreen(notificationViewModel)
            }

            composable(DestinationScreen.CurrentRecruitment.route) {
                // screen
                RecruitmentScreen(navController, recruitmentViewModel)
            }

            composable(DestinationScreen.Menu.route) {
                // screen
                MenuScreen(navController)
            }

            composable(DestinationScreen.CommunityAdmin.route) {
                //    ComminityAdminScreen(navController, viewModel = authenticationViewModel)
                ComminityAdminScreen(comminityViewModel)
            }

            composable(DestinationScreen.Community.route) {
                ComminityScreen(navController, comminityViewModel)
            }

            composable(DestinationScreen.Topics.route) {
                val themeId = it.arguments?.getString("themeId")

                themeId?.let {
                    TopicsScreen(navController, comminityViewModel, authenticationViewModel, it)
                }

            }

            composable(DestinationScreen.Comments.route) {
                val themeId = it.arguments?.getString("themeId")
                val topicId = it.arguments?.getString("topicId")

                themeId?.let { theme ->
                    topicId?.let {
                        CommentScreen(
                            themeId = theme,
                            topicId = it,
                            navController = navController,
                            comminityVm = comminityViewModel,
                            authenticationViewModel = authenticationViewModel
                        )
                    }
                }

            }

            composable(DestinationScreen.AnnouncementDetail.route) {
                val detail_url = it.arguments?.getString("detail_url")
                detail_url?.let {
                    AnnouncementDetailScreen(detail_url)
                }

            }

            composable(DestinationScreen.RecruitmentDetail.route) {
                val detail_url = it.arguments?.getString("detail_url")
                detail_url?.let {
                    AnnouncementDetailScreen(detail_url)
                    println("detail_url : $detail_url")
                }
            }

            composable(DestinationScreen.SignUp.route) {
                SignUpScreen(navController, authenticationViewModel)
            }
            composable(DestinationScreen.Login.route) {
                LoginScreen(navController, authenticationViewModel)
            }

            composable(DestinationScreen.Profile.route) {
                ProfileScreen(navController, authenticationViewModel)
            }

            composable(DestinationScreen.Settings.route) {
                SettingsScreen(
                    navController = navController,
                    settingsViewModel = settingsViewModel,
                    notificationViewModel = notificationViewModel
                )
            }

            composable(DestinationScreen.AboutUs.route) {
                AboutUsScreen(navController = navController)
            }

            composable(DestinationScreen.Suggestion.route) {
                SuggestionScreen(navController = navController, suggestionViewModel)
            }

        }
    }
}

@HiltAndroidApp
class LcApplication : Application() {

}