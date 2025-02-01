package com.mobile.tskpersonelteminapp.utils


import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(route)
        launchSingleTop = true
    }
}


fun LoggedIn(
    viewModel: AuthenticationViewModel,
    onVerified: () -> Unit,
    notVerified: () -> Unit
) {/*
    val alreadySignIn = remember {
        mutableStateOf(false)
    }*/

    val signIn = viewModel.signIn.value

    if (signIn) {
        onVerified.invoke()
    } else {
        notVerified.invoke()
    }

}