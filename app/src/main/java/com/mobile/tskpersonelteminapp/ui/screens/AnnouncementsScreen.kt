package com.mobile.tskpersonelteminapp.ui.screens


import android.app.Activity
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.data.NBANNOUNCEMENT
import com.mobile.tskpersonelteminapp.data.NBRECRUITMENT
import com.mobile.tskpersonelteminapp.data.models.Announcement
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


@Composable
fun AnnouncementsScreen(navController: NavController, viewModel: AnnouncementsViewModel) {

    val errorMessage by viewModel.errorMessage

    // Hata mesajını dinle ve göster
    ObserveErrorMessage(errorMessage)


    val inProcess = viewModel.inProcess.value
    val announcements = viewModel.announcements.value
    val context = LocalContext.current
    val intent = (context as? Activity)?.intent
    var notificationTitle = remember{intent?.getStringExtra("notification_title")}
    var announcementTitle = remember{intent?.getStringExtra("announcement_title")}
    val isAnnouncement = remember {  mutableStateOf<Boolean?>(null)}

    LaunchedEffect(notificationTitle, announcementTitle) {
        Log.d("DEBUG", "LaunchedEffect - notification_title: $notificationTitle")
        Log.d("DEBUG", "LaunchedEffect - announcement_title: $announcementTitle")

        isAnnouncement.value =
            if (intent?.getStringExtra("notification_title") == NBANNOUNCEMENT)
                true
            else if (intent?.getStringExtra("notification_title") == NBRECRUITMENT)
                false
            else {
                Log.d("isANNOUNCEMENT", "AnnouncementsScreen: return launchedeffect")
                return@LaunchedEffect
            }
        //title.value = intent.getStringExtra("title")
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: " + isAnnouncement.value)
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: intent value isA " + intent.getStringExtra("notification_title"))
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: intent value title" + intent.getStringExtra("announcement_title"))


        if (isAnnouncement.value!! && announcementTitle != null) {
            val notification_announcement = announcements.find {
                it.title == announcementTitle
            }
            Log.d("isANNOUNCEMENT", "AnnouncementsScreen: title" + notification_announcement?.title)
            Log.d("isANNOUNCEMENT", "AnnouncementsScreen: uri: " + notification_announcement?.detail_url)


            notification_announcement?.let {
                navigateTo(
                    navController,
                    route = DestinationScreen.AnnouncementDetail.createRoute(it.detail_url!!)
                )
            }
            // Intent'in tekrar kullanılmasını önlemek için intent'ten aldığımız verileri null yap
            notificationTitle = null
            announcementTitle = null
        } else {
            navigateTo(
                navController,
                route = DestinationScreen.CurrentRecruitment.route
            )
        }

    }
 /**   val isAnnouncement = remember { mutableStateOf(true) }
    val title = remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {

        val activity = context as? Activity
        val intent = activity?.intent
        Log.d("INTENT_DEBUG", "Activity null mu? ${activity == null}")
        title.value = intent?.getStringExtra("announcement_title")
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: " + isAnnouncement.value)
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: intent value " + intent?.getStringExtra("notification_title"))
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: intent value " + intent?.getStringExtra("announcement_title"))

        isAnnouncement.value =
            if (intent?.getStringExtra("notification_title") == NBANNOUNCEMENT)
                true
            else if (intent?.getStringExtra("notification_title") == NBRECRUITMENT)
                false
            else {
                Log.d("isANNOUNCEMENT", "AnnouncementsScreen: return launchedeffect")
                return@LaunchedEffect
            }
        //title.value = intent.getStringExtra("title")
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: " + isAnnouncement.value)
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: intent value isA " + intent.getStringExtra("notification_title"))
        Log.d("isANNOUNCEMENT", "AnnouncementsScreen: intent value title" + intent.getStringExtra("announcement_title"))


        if (isAnnouncement.value && title.value != null) {
            val notification_announcement = announcements.find {
                it.title == title.value
            }
            Log.d("isANNOUNCEMENT", "AnnouncementsScreen: title" + notification_announcement?.title)
            Log.d("isANNOUNCEMENT", "AnnouncementsScreen: uri: " + notification_announcement?.detail_url)


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

    }*/

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
                        color = line
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