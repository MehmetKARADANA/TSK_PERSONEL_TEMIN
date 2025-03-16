package com.mobile.tskpersonelteminapp.ui.screens


import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.data.NBANNOUNCEMENT
import com.mobile.tskpersonelteminapp.data.NBRECRUITMENT
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.ui.components.CommonProgressBar
import com.mobile.tskpersonelteminapp.ui.components.CustomCard
import com.mobile.tskpersonelteminapp.ui.components.EmptyHeader
import com.mobile.tskpersonelteminapp.ui.theme.background
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AnnouncementsViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun AnnouncementsScreen(navController: NavController, viewModel: AnnouncementsViewModel,latestIntent: State<Intent?>) {

    val errorMessage by viewModel.errorMessage

    // Hata mesajını dinle ve göster
    ObserveErrorMessage(errorMessage)


    val inProcess = viewModel.inProcess.value
    val announcements = viewModel.announcements.value

    val notificationTitle = remember{ mutableStateOf<String?>(null) }
    val announcementTitle = remember{ mutableStateOf<String?>(null) }
    val isAnnouncement = remember {  mutableStateOf<Boolean?>(null)}

    LaunchedEffect(latestIntent.value) {

        announcementTitle.value=latestIntent.value?.getStringExtra("announcement_title")
        notificationTitle.value=latestIntent.value?.getStringExtra("notification_title")
        isAnnouncement.value =
            when (notificationTitle.value) {
                NBANNOUNCEMENT -> true
                NBRECRUITMENT -> false
                else -> {
                    return@LaunchedEffect
                }
            }

        if (isAnnouncement.value!!) {
            latestIntent.value?.removeExtra("announcement_title")
            latestIntent.value?.removeExtra("notification_title")
            val notification_announcement = announcements.find {
                it.title == announcementTitle.value
            }

            notification_announcement?.let {
                navigateTo(
                    navController,
                    route = DestinationScreen.AnnouncementDetail.createRoute(it.detail_url!!)
                )
            }
        } else {
            navigateTo(
                navController,
                route = DestinationScreen.CurrentRecruitment.route
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
    ) {
        EmptyHeader("Duyurular")
        if (inProcess) {
            CommonProgressBar()
        } else if (errorMessage != null) {
            //   Text(text = "Error :b$errorMessage")
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
            if (announcements.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(announcements) {
                        CustomCard(
                            title = it.title!!,
                            date = it.date!!,
                            modifier = Modifier.clickable {
                                navigateTo(
                                    navController = navController,
                                    route = DestinationScreen.AnnouncementDetail.createRoute(it.detail_url!!)
                                )
                            })
                    }

                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "❌ Mevcut Duyuru Bulunmuyor",
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Gray
                    )
                }
            }


        }
        BottomNavigationMenu(
            selectedItem = BottomNavigationMenuItem.ANNOUNCEMENT,
            navController = navController
        )
    }

}