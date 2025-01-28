package com.mobile.tskpersonelteminapp.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.viewmodels.RecruitmentViewModel

@Composable
fun RecruitmentScreen(navController: NavController,viewModel: RecruitmentViewModel) {

    val inProcess=viewModel.inProcess.value
    val errorMessage=viewModel.errorMessage.value
    val recruitments = viewModel.recruitments.value

    Column(modifier = Modifier.fillMaxSize()) {
       /* Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Current Recruitment")
        }*/
        if(inProcess){
            CircularProgressIndicator()
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
                        Text(text = it.title.toString(), modifier = Modifier.clickable {
                            navigateTo(
                                navController=navController,
                                route = DestinationScreen.RecruitmentDetail.createRoute(it.detail_url!!)
                            )
                        })
                        Text(text = "---------")
                    }
                }
            }
        }
        BottomNavigationMenu(selectedItem = BottomNavigationMenuItem.RECRUITMENT,navController=navController)
    }
}