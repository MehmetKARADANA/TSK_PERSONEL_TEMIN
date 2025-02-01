package com.mobile.tskpersonelteminapp.utils


import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(route)
        launchSingleTop = true
    }
}


fun CheckSignedIn(
    viewModel: AuthenticationViewModel,
    navController: NavController
) {/*
    val alreadySignIn = remember {
        mutableStateOf(false)
    }*/

    val signIn = viewModel.signIn.value
    if(signIn){
        navigateTo(navController,DestinationScreen.Menu.route)
    }

}