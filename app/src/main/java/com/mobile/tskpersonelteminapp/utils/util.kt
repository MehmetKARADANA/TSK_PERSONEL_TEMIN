package com.mobile.tskpersonelteminapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

fun navigateTo(navController: NavController,route : String){
    navController.navigate(route){
        popUpTo(route)
        launchSingleTop=true
    }
}

@Composable
fun LoggedIn(){

}