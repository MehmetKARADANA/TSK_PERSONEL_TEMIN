package com.mobile.tskpersonelteminapp.utils


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.data.Event
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel

@Composable
fun ObserveErrorMessage(event: Event<String>?) {
    val context = LocalContext.current

    event?.getContentIfNotHandled()?.let { message ->
        LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}

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