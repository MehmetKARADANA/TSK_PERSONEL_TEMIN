package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.mobile.tskpersoneltemin.ui.components.BottomNavigationMenu
import com.mobile.tskpersoneltemin.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.viewmodels.AnnouncementsViewModels


@Composable
fun AnnouncementsScreen(navController: NavController, viewModel: AnnouncementsViewModels) {
    val announcements by viewModel.announcements.collectAsState()
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    if(isLoading){
        CircularProgressIndicator()
    }else if (errorMessage != null){
        Text(text = "Error :"+errorMessage)
    }else{
        LazyColumn {
            items(announcements){
                Text(text = it.title.toString())
            }
        }
    }
    BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.ANNOUNCEMENT, navController = navController)

}