package com.mobile.tskpersonelteminapp.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mobile.tskpersoneltemin.ui.components.BottomNavigationMenu
import com.mobile.tskpersoneltemin.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.viewmodels.AnnouncementsViewModels


@Composable
fun AnnouncementsScreen(navController: NavController, viewModel: AnnouncementsViewModels) {

    val inProcess=viewModel.inProcess.value
    val errorMessage=viewModel.errorMessage.value
    val announcements=viewModel.announcements.value
Column(modifier = Modifier.fillMaxSize()) {
    if (inProcess) {
        CircularProgressIndicator()
    } else if (errorMessage != null) {
            Text(text = "Error :" + errorMessage)

    } else {
        if (announcements.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                items(announcements) {
                    Text(text = it.title.toString())
                    Text("------------------")
                }

            }
        }


    }
    BottomNavigationMenu(
        selectedItem = BottomNavigationMenuItem.ANNOUNCEMENT,
        navController = navController
    )
}

}