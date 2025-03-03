package com.mobile.tskpersonelteminapp.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.CommonProgressBar
import com.mobile.tskpersonelteminapp.ui.components.CustomCard
import com.mobile.tskpersonelteminapp.ui.components.EmptyHeader
import com.mobile.tskpersonelteminapp.ui.theme.background
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.viewmodels.RecruitmentViewModel

@Composable
fun RecruitmentScreen(navController: NavController,viewModel: RecruitmentViewModel) {

    val errorMessage by viewModel.errorMessage

    ObserveErrorMessage(errorMessage)


    val inProcess=viewModel.inProcess.value
    val recruitments = viewModel.recruitments.value

    Column(modifier = Modifier.fillMaxSize().background(color = background)) {
        EmptyHeader("Teminler")
        if(inProcess){
            CommonProgressBar()
        }else if(errorMessage != null){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Error : $errorMessage")
            }
        }else {
            if(recruitments.isNotEmpty()){
                LazyColumn (modifier = Modifier.fillMaxWidth().weight(1f)){
                    items(recruitments){
                        CustomCard(title = it.title!!, date = it.date!!, modifier = Modifier.clickable {
                            navigateTo(
                                navController = navController,
                                route = DestinationScreen.RecruitmentDetail.createRoute(it.detail_url!!)
                            )
                        })
                    }
                }
            }else{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("❌ Mevcut Temin Bulunmuyor", fontFamily = FontFamily.SansSerif)
                }
            }
        }
        BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.RECRUITMENT,navController=navController)
    }
}